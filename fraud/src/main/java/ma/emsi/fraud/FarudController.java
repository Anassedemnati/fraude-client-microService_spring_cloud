package ma.emsi.fraud;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ma.emsi.clients.fraud.FraudChekResponse;

@RestController
@RequestMapping("api/v1/fraud-check")
@Data
@Slf4j
public class FarudController {
    private final FarudCheckService farudCheckService;
    @GetMapping(path = "{customerId}")
    public FraudChekResponse isFraudster(@PathVariable("customerId") Integer customerId){
        boolean isfraudulentCustomer = farudCheckService.inFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}",customerId);
        return new FraudChekResponse(isfraudulentCustomer);
    }
}
