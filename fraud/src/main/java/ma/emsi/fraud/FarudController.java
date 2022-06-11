package ma.emsi.fraud;


import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@Data
public class FarudController {
    private final FarudCheckService farudCheckService;
    @GetMapping(path = "{customerId}")
    public FraudChekResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isfraudulentCustomer = farudCheckService.inFraudulentCustomer(customerId);
        return new FraudChekResponse(isfraudulentCustomer);
    }
}
