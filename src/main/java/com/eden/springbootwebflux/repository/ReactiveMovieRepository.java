package com.eden.springbootwebflux.repository;

import com.eden.springbootwebflux.domain.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class ReactiveMovieRepository {
  private static List<Movie> movie = new ArrayList<>();

  // 1차
  static {
    movie.add(new Movie("블랙 팬서: 와칸다 포에버", 64));
    movie.add(new Movie("데시벨", 79));
    movie.add(new Movie("동감", 93));
    movie.add(new Movie("폴: 600미터", 83));
    movie.add(new Movie("인생은 아름다워", 70));
  }

  public static Movie containsId(String id) {
    for (Movie object : movie) {
      if (object.getName() == id) {
        return object;
      }
    }
    return null;
  }

  // 2차
  @PostConstruct
  public void init() {
    for (int i = 0; i < 25; i++) {
      var Mov = generate();
      movie.add(Mov);
    }
  }

  public Flux<Movie> findAll() {
    return Flux.fromIterable(movie).delayElements(Duration.ofMillis(100));
  }

//  public Mono<Movie> findById(String id) {
//    Movie movieItem = null;
//    movieItem = containsId(id);
//    if (movieItem == null) {
//      return null;
//    }
//    return Mono.just(movieItem);
//  }
//
//  public Mono<Movie> save(Mono<Movie> order) {
//    return order.map(this::save);
//  }
//
//  public Movie save(Movie movieItem) {
//    movie.add(movieItem);
//    return movieItem;
//  }

  public Movie generate() {
    var score = ThreadLocalRandom.current().nextInt(100);
    return new Movie(UUID.randomUUID().toString(), score);
  }
}
