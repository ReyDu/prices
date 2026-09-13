package com.jjimenez.prices.fixtures;

import com.jjimenez.prices.application.usecase.PriceCriteria;
import java.time.LocalDateTime;

public class PriceCriteriaFixtures {

  public static PriceCriteria createValidCriteria() {
    return PriceCriteria.builder()
        .brandId(1)
        .productId(35455L)
        .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
        .build();
  }

  public static PriceCriteria createNotFoundCriteria() {
    return PriceCriteria.builder()
        .brandId(1)
        .productId(35455L)
        .applicationDate(LocalDateTime.parse("2050-01-01T10:00:00"))
        .build();
  }

}
