package ma.emsi.fraud;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
@AllArgsConstructor
public class FarudCheckService {

    private final FarudCheckHistoryRepository farudCheckHistoryRepository;

    public boolean inFraudulentCustomer(Integer custemerId){
        farudCheckHistoryRepository.save(
                FraudChekHistory.builder()
                        .CustomerId(custemerId)
                        .isFroudster(false)
                        .cratedAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }

}
