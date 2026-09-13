package com.jjimenez.prices.fixtures;

import com.jjimenez.prices.domain.model.Brand;
import com.jjimenez.prices.domain.model.Currency;
import com.jjimenez.prices.domain.model.Price;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PriceFixtures {

  public static Price createValidPrice() {
    return Price.builder()
        .brand(Brand.ZARA)
        .productId(35455L)
        .startDate(LocalDateTime.parse("2020-06-14T00:00:00"))
        .endDate(LocalDateTime.parse("2020-12-31T23:59:59"))
        .priceList(1)
        .priority(0)
        .price(new BigDecimal("35.50"))
        .currency(Currency.EUR)
        .build();
  }

  public static Price createAlternativePrice() {
    return Price.builder()
        .brand(Brand.ZARA)
        .productId(35455L)
        .startDate(LocalDateTime.parse("2020-06-14T15:00:00"))
        .endDate(LocalDateTime.parse("2020-06-14T18:30:00"))
        .priceList(2)
        .priority(1)
        .price(new BigDecimal("25.45"))
        .currency(Currency.EUR)
        .build();
  }

  public static List<Price> createPriceList() {
    return List.of(createValidPrice(), createAlternativePrice());
  }
}
