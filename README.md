# RestTemplate and Web Client

- [스프링 부트 2.0 Day 34. RestTemplate과 WebClient](https://www.youtube.com/watch?v=a4Hiz3pqizg&t=1738s)
- [GitHub API](https://docs.github.com/en/rest)

## RestTemplate

- [Blocking Call](https://github.com/BAEKJungHo/resttemplate-webclient/commit/70cbe126dba97e7d99bb0c6646d01dd321400617)

> [Documents](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.rest-client.resttemplate)

## WebClient

- [NonBlocking Call](#)
- WebClient.Builder instances are stateful
  - WebClient.Builder 자체는 싱글톤으로 구성되어있기 때문에, scope 를 메서드로 한정하고, 상태로 인한 동시성이슈가 발생하지 않도록 하려면 아래와 같은 방법을 사용
  - `WebClient.Builder other = builder.clone();`

> [Documents](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#io.rest-client.webclient.customization)
