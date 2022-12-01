# Webflux

![image](https://user-images.githubusercontent.com/47589936/204944548-d839f3f6-5a40-445e-a2ad-568395083d6a.png)


> Spring Framework 5에서 새롭게 추가된 모듈
>
> 클라이언트, 서버에서 Reactive 스타일의 애플리케이션 개발을 도와주는 모듈이며 non-blocking으로 Reactive Stream을 지원

## 🧩 Spring WebFlux가 만들어진 이유는 무엇인가?

- 적은 스레드로 동시성을 처리하고, 보다 적은 하드웨어 자원으로 확장하기 위해

- 함수형 프로그래밍 - Java 8에서 람다식 추가 됨으로 함수 API 가능성 확장

- MSA 아키텍처로 가면서 작고 많은 모듈로 쪼개지고, 배로 증가한 통신 트래픽을 기존 blocking 방식에서 처리 한계(스레드풀 발생 확률 ↑)

> Webflux 는 Asynchronous Non-blocking I/O 방식을 활용하여 성능을 끌어 올릴 수 있는 장점

## 🤔 Spring MVC vs. WebFlux

![image](https://user-images.githubusercontent.com/47589936/204944573-ba887df1-17d7-447a-b322-1121d3cf73af.png)

- 동작하는 Spring MVC 애플리케이션이 있다면 Spring WebFlux로 변환 필요 ❌
- Spring WebFlux는 Java 8 lambda 또는 Kotlin과 함께 사용하는 가볍고 기능적인 웹 프레임 워크에 유용
- 애플리케이션이 JPA, JDBC 또는 네트워킹 API에 의존하는 경우 Spring MVC가 최선의 선택

## 💡 Mono와 Flux

> Spring Webflux에서 사용하는 Reactive Library 👉 Reactor
>
> Reactor = Reactive Streams의 구현체

Flux와 Mono는 Reactor 객체이며, 차이점은 발행하는 데이터 갯수

| Mono            |Flux|
|-----------------|------|
| 0 ~ 1 개의 데이터 전달 |0 ~ N 개의 데이터 전달|
|![image](https://user-images.githubusercontent.com/47589936/204944587-a3043ae9-5bed-42d5-9be1-5a23b792de0b.png)|![image](https://user-images.githubusercontent.com/47589936/204944593-88ace0c5-3042-4d5b-948d-09c0b89513af.png)|

## Terminal로 비교하기

````shell
// 10만건 데이터 Redis에 로드
curl -i localhost:8080/load

// Servlet 조회
curl -i localhost:8080/normal-list

// Reactive 조회
curl -i localhost:8080/reactive-list
````

| Servlet Stack | Reactive Stack                                                                                                            |
|-----|-----|
|![Nov-24-2022 10-09-17](https://user-images.githubusercontent.com/47589936/203674415-a0d8e2ca-92fc-4045-bb51-60235b65830d.gif)|![Nov-24-2022 10-25-39](https://user-images.githubusercontent.com/47589936/203674426-9ef05528-e8a0-452d-a90f-6022302c3b84.gif)|

## Test 코드로 비교하기

- IOTest.java

## Web으로 비교하기

http://localhost:8080

| Servlet Stack | Reactive Stack                                                                                                            |
|----------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------|
|![servlet-stack](https://user-images.githubusercontent.com/47589936/203270991-f87292ef-4b1a-4523-9818-1edb51b8c6f2.gif)| ![reactive-stack](https://user-images.githubusercontent.com/47589936/203270981-6a926a8b-3654-4ef3-90eb-860b5f4bd233.gif)  |

## 참고 자료

* [Web on Reactive Stack](https://docs.spring.io/spring-framework/docs/current/reference/html/web-reactive.html)
* [Spring Web Reactive](https://www.devkuma.com/docs/spring-web-reactive/1-spring-webflux/)
* [10분 테코톡 Black vs Non-Block & Sync vs Async](https://youtu.be/IdpkfygWIMk)
* [Webflux의 개념 / Spring MVC와 간단비교](https://devuna.tistory.com/108)
* [Raw Performance Numbers - Spring Boot 2 Webflux vs. Spring Boot 1](https://dzone.com/articles/raw-performance-numbers-spring-boot-2-webflux-vs-s)
* [Spring WebFlux는 어떻게 적은 리소스로 많은 트래픽을 감당할까?](https://alwayspr.tistory.com/44)
* [Spring WebFlux란?](https://heeyeah.github.io/spring/2020-02-29-web-flux/) 

