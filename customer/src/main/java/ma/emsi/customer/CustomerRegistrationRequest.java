package ma.emsi.customer;

public record CustomerRegistrationRequest(
         String firstName,
         String lastName,
         String email
) {
}
