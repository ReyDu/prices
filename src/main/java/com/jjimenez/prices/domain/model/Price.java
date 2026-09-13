package com.jjimenez.prices.domain.model;

import com.jjimenez.prices.domain.exception.PriceNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import lombok.Builder;

@Builder(toBuilder = true)
public record Price(
    Brand brand,
    Long productId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price,
    Currency currency,
    Integer priority
) {

  public static final String NO_PRICE_FOUND_MSG = "No price found for the given criteria";

  public static Price selectApplicable(List<Price> candidates) {
    if (candidates == null || candidates.isEmpty()) {
      throw new PriceNotFoundException(NO_PRICE_FOUND_MSG);
    }
    return candidates.stream()
        .max(Comparator.comparingInt(Price::priority))
        .orElseThrow(() -> new PriceNotFoundException(NO_PRICE_FOUND_MSG));
  }

}
