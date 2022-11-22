package com.eden.springbootwebflux.controller;

import com.eden.springbootwebflux.repository.ReactiveMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class MovieController {

  private final ReactiveMovieRepository movieRepository;

  @RequestMapping("/list")
  public String list(Model model) {
    return "list";
  }

  @RequestMapping("/reactive")
  public String reactive(final Model model) {
    IReactiveDataDriverContextVariable reactiveDataDrivenMode =
        new ReactiveDataDriverContextVariable(movieRepository.findAll(), 1);

    model.addAttribute("movies", reactiveDataDrivenMode);
    return "reactive";
  }

  @RequestMapping("/servlet")
  public Mono<String> servlet(Model model) {
    var movies = movieRepository.findAll();
    model.addAttribute("movies", movies);
    return Mono.just("servlet");
  }
}
