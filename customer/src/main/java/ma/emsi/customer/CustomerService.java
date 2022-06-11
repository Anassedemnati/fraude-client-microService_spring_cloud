package ma.emsi.customer;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService{
    private final CustomerRepository customerRepository;
    public void registerCustomer(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();
    //TODO : check if email is valid
    //TODO : check if email not taken
    //store customer to db
        customerRepository.save(customer);
    }
}
