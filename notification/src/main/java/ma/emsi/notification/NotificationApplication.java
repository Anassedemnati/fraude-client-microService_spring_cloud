package ma.emsi.notification;

import ma.emsi.amqp.RabbitMQMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;


@EnableEurekaClient
@SpringBootApplication(
        scanBasePackages = {
                "ma.emsi.notification",
                "ma.emsi.amqp",
        }
)
@EnableFeignClients(
        basePackages = "ma.emsi.clients"
)


public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class,args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer,
//                                        NotificationConfig notificationConfig){
//        return args -> {
//            producer.publish(
//                    new Person("Anasse",22),
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey()
//            );
//
//        };
//    }
//    record Person(String name,int age){}
}
