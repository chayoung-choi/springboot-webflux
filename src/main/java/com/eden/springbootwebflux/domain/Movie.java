package com.eden.springbootwebflux.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Movie {
  private String name;
  private Integer score;
}
