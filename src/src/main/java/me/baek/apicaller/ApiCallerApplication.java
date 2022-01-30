package me.baek.apicaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

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
