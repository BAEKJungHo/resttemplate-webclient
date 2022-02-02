package me.baek.apicaller;

import me.baek.error.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class ApiCaller {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    WebClient.Builder webClientBuilder;

    @Autowired
    RestTemplateResponseErrorHandler restTemplateResponseErrorHandler;

    public void blockingCallWithRestTemplate() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Blocking Call 을 꼭 써야하나 ?
        // 현재는 repo 호출이 끝날 때 까지, commit 호출을 하지 않음
        // 두 API 호출은 서로 디펜던시가 없음
        RestTemplate restTemplate = restTemplateBuilder.errorHandler(restTemplateResponseErrorHandler).build();
        GitHubRepository[] repositories = restTemplate.getForObject("https://api.github.com/users/baekjungho/repos", GitHubRepository[].class);
        Arrays.stream(repositories).forEach(repo -> {
            System.out.println("repo : " + repo.getUrl());
        });

        GitHubCommit[] commits = restTemplate.getForObject("https://api.github.com/repos/baekjungho/TIL/commits", GitHubCommit[].class);
        Arrays.stream(commits).forEach(commit -> {
            System.out.println("commit : " + commit.getUrl());
        });

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }

    public void nonBlockingWithWebClientMono() {
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
    }

    public void nonBlockingWithWebClientFlux() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        WebClient webClient = webClientBuilder.baseUrl("https://api.github.com").build();

        Flux<GitHubRepository> repositoriesFlux = webClient.get().uri("/users/baekjungho/repos")
                .retrieve()
                .bodyToFlux(GitHubRepository.class);
        repositoriesFlux.subscribe(r -> System.out.println("repo: " + r.getUrl()));

        Flux<GitHubCommit> commitsFlux = webClient.get().uri("/repos/baekjungho/TIL/commits")
                .retrieve()
                .bodyToFlux(GitHubCommit.class);
        commitsFlux.subscribe(c -> System.out.println("commit: " + c.getSha()));

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
