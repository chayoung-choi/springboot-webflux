package com.eden.springbootwebflux;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = DEFINED_PORT, properties = {"server.port=8080"})
public class IOTest {
  private static final String THREE_SECOND_URL = "http://localhost:8080/3second";
  private static final String INCREMENT_SECOND_URL = "http://localhost:8080/increment";
  private static final int LOOP_COUNT = 100;

  private final WebClient webClient = WebClient.create();
  private final CountDownLatch count = new CountDownLatch(LOOP_COUNT);

  @BeforeEach
  public void setup() {
    System.setProperty("reactor.netty.ioWorkerCount", "1");
  }

  @Test
  @DisplayName("[blocking]API 5번 호출하면, 15초대가 나온다")
  public void blocking() {
    final RestTemplate restTemplate = new RestTemplate();

    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    for (int i = 0; i < 5; i++) {
      final ResponseEntity<String> response =
          restTemplate.exchange(THREE_SECOND_URL, HttpMethod.GET, HttpEntity.EMPTY, String.class);
      System.out.println(response.getBody());
      assertThat(response.getBody()).contains("success");
    }

    stopWatch.stop();
    System.out.println("소요 시간 : " + stopWatch.getTotalTimeSeconds());
  }

  @Test
  public void thread() {
    System.out.println(1);

    new Thread(() -> System.out.println(2)).start();

    System.out.println(3);
  }

  @Test
  public void nonBlocking1() throws InterruptedException {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    this.webClient
        .get()
        .uri(THREE_SECOND_URL)
        .retrieve()
        .bodyToMono(String.class)
        .doOnNext(System.out::println)
        .subscribe();

    stopWatch.stop();

    System.out.println("소요 시간 : " + stopWatch.getTotalTimeSeconds());
    Thread.sleep(5000);
  }


  @Test
  public void nonBlocking2() throws InterruptedException {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();

    this.webClient
        .get()
        .uri(THREE_SECOND_URL)
        .retrieve()
        .bodyToMono(String.class)
        .log()
        .subscribe(it -> {
          stopWatch.stop();
          System.out.println("소요 시간 : " + stopWatch.getTotalTimeSeconds());
        });

    Thread.sleep(5000);
  }

  @Test
  @DisplayName("[nonBlocking]동일한 15초 동안 더 많은 API를 호출한다")
  public void nonBlocking3() throws InterruptedException {
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    for (int i = 0; i < 25; i++) {
      this.webClient
          .get()
          .uri(THREE_SECOND_URL)
          .retrieve()
          .bodyToMono(String.class)
          .subscribe(it -> {
            count.countDown();
            System.out.println(it);
          });
    }

    count.await(15, TimeUnit.SECONDS);
    stopWatch.stop();
    System.out.println("소요 시간 : " + stopWatch.getTotalTimeSeconds());
  }
}
