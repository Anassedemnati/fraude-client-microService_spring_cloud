package ma.emsi.customer;


import lombok.AllArgsConstructor;
import ma.emsi.clients.fraud.FraudChekResponse;
import ma.emsi.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    //TODO : check if email is valid
    //TODO : check if email not taken
        customerRepository.saveAndFlush(customer);
        // TODO : check if fraudster


        FraudChekResponse fraudChekResponse = fraudClient.isFraudster(customer.getId());

        if (fraudChekResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
//        //store customer to db
//        customerRepository.save(customer);
        // TODO : send notification
    }
}
