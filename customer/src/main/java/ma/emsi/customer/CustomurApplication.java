package ma.emsi.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(
        basePackages = "ma.emsi.clients"
)
public class CustomurApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomurApplication.class,args);
    }
}
//TODO stop in lesson 7 docker