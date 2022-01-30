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
    WebClient.Builder webClientBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ApiCallerApplication.class, args);
    }

    @Bean
    public ApplicationRunner appRunner() {
        return args -> {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            WebClient webClient = webClientBuilder.baseUrl("https://api.github.com").build();

            // Mono 는 실제로 구독(Subscription) 을 하기 전에는 Flow 가 발생하지 않는다.
            // Non-Blocking
            Mono<GitHubRepository[]> repositoriesMono = webClient.get().uri("/users/baekjungho/repos")
                    .retrieve()
                    .bodyToMono(GitHubRepository[].class);

            Mono<GitHubCommit[]> commitsMono = webClient.get().uri("/repos/baekjungho/TIL/commits")
                    .retrieve()
                    .bodyToMono(GitHubCommit[].class);

            repositoriesMono.doOnSuccess(ra -> {
                Arrays.stream(ra).forEach(r -> {
                    System.out.println("repo: " + r.getUrl());
                });
            }).subscribe();

            commitsMono.doOnSuccess(ca -> {
                Arrays.stream(ca).forEach(c -> {
                    System.out.println("commit: " + c.getSha());
                });
            }).subscribe();

            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        };
   }

}
