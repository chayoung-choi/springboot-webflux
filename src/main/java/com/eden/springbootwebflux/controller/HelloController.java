package com.eden.springbootwebflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

@RestController
public class HelloController {

  @GetMapping("/stream/limit")
  Flux<Map<String, Integer>> limitStream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    return Flux.fromStream(stream.limit(10))
        .map(i -> {
          System.out.println(i);
          return Collections.singletonMap("value", i);
        });
  }

  @GetMapping("/stream")
  Flux<Map<String, Integer>> stream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    return Flux.fromStream(stream)
        .map(i -> {
          System.out.println(i);
          return Collections.singletonMap("value", i);
        });
  }

  @GetMapping("/stream/slow")
  Flux<Map<String, Integer>> slowStream() {
    Stream<Integer> stream = Stream.iterate(0, i -> i + 1);
    return Flux.fromStream(stream).zipWith(Flux.interval(Duration.ofSeconds(1))) // 속도 조절(zipWith(Flux.interval(Duration.ofSeconds(1)))
        .map(tuple -> {
          System.out.println(tuple.getT1());
          return Collections.singletonMap("value", tuple.getT1());
        });
  }

  @PostMapping("/echo")
  Mono<String> echo(@RequestBody Mono<String> body) {
    return body.map(String::toUpperCase);
  }

}
