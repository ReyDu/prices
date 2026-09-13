package com.jjimenez.prices.application.usecase;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record PriceCriteria(
    Integer brandId,
    Long productId,
    LocalDateTime applicationDate
) {

}
