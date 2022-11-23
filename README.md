# Webflux

> Spring Framework 5에서 새롭게 추가된 모듈
> 
> 클라이언트, 서버에서 Reactive 스타일의 애플리케이션 개발을 도와주는 모듈이며 non-blocking으로 Reactive Stream을 지원

## 왜 Reactive인가?
- 비동기/논블록을 이용해서 더 적은 자원으로 더 많은 트래픽을 처리하기 위함

## Terminal로 비교하기
````shell
// 10만건 데이터 Redis에 로드
curl -i localhost:8080/load

// Servlet 조회
curl -i localhost:8080/normal-list

// Reactive 조회
curl -i localhost:8080/reactive-list
````

## Test 코드로 비교하기
- IOTest.java

## Web으로 비교하기
http://localhost:8080

### Servlet Stack

![servlet-stack](https://user-images.githubusercontent.com/47589936/203270991-f87292ef-4b1a-4523-9818-1edb51b8c6f2.gif)

### Reactive Stack

![reactive-stack](https://user-images.githubusercontent.com/47589936/203270981-6a926a8b-3654-4ef3-90eb-860b5f4bd233.gif)


