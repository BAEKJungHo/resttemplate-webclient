package me.baek;

import me.baek.apicaller.ApiCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

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
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            for(int i = 0; i < 10; i++) {
                apiCaller.nonBlockingWithWebClientFlux();
            }
            stopWatch.stop();
            System.out.println(stopWatch.getTotalTimeSeconds());
        };
   }
}
