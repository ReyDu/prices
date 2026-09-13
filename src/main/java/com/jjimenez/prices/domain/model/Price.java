package com.jjimenez.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Price(
    Long brandId,
    Long productId,
    Integer priceList,
    LocalDateTime startDate,
    LocalDateTime endDate,
    BigDecimal price,
    Currency currency
) {

}
