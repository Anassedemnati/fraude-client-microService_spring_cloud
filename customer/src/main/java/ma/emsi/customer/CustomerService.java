package ma.emsi.customer;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
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
        FraudChekResponse fraudChekResponse = restTemplate.getForObject(
                "http://localhost:8082/api/v1/fraud-check/{customerId}",
                FraudChekResponse.class,
                customer.getId()

        );
        if (fraudChekResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }
//        //store customer to db
//        customerRepository.save(customer);
        // TODO : send notification
    }
}
