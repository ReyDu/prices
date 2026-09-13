package com.jjimenez.prices.application.port;

import com.jjimenez.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface GetPricesPort {

  List<Price> findPrices(Long brandId, Long productId, LocalDateTime applicationDate);

}
