package ma.emsi.customer;


import lombok.AllArgsConstructor;
import ma.emsi.amqp.RabbitMQMessageProducer;
import ma.emsi.clients.fraud.FraudChekResponse;
import ma.emsi.clients.fraud.FraudClient;
import ma.emsi.clients.notification.NotificationClient;
import ma.emsi.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;
    public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    //TODO : check if email is valid
    //TODO : check if email not taken
        //store customer to db
        customerRepository.saveAndFlush(customer);
        //  check if fraudster
        FraudChekResponse fraudChekResponse = fraudClient.isFraudster(customer.getId());

        if (fraudChekResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //  send notification

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Emsi...", customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"

        );
    }
}
