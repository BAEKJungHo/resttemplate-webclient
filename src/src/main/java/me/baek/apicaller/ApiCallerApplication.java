package me.baek.apicaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class ApiCallerApplication {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    public static void main(String[] args) {
        SpringApplication.run(ApiCallerApplication.class, args);
    }

    @Bean
    public ApplicationRunner appRunner() {
        return args -> {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            // Blocking Call 을 꼭 써야하나 ?
            // 현재는 repo 호출이 끝날 때 까지, commit 호출을 하지 않음
            // 두 API 호출은 서로 디펜던시가 없음
            RestTemplate restTemplate = restTemplateBuilder.build();
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
        };
   }

}
