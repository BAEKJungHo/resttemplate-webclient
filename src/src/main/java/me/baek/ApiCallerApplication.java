package me.baek;

import me.baek.apicaller.ApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiCallerApplication {

    @Autowired
    ApiCaller apiCaller;

    public static void main(String[] args) {
        SpringApplication.run(ApiCallerApplication.class, args);
    }

    @Bean
    public ApplicationRunner appRunner() {
        return args -> {
            apiCaller.nonBlockingWithWebClientFlux();
        };
   }
}
