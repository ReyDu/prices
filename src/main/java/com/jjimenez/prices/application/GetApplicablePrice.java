package com.jjimenez.prices.application;

import com.jjimenez.prices.application.port.GetPricesPort;
import com.jjimenez.prices.application.usecase.GetApplicablePriceUseCase;
import com.jjimenez.prices.application.usecase.PriceCriteria;
import com.jjimenez.prices.domain.model.Price;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetApplicablePrice implements GetApplicablePriceUseCase {

  private final GetPricesPort getPricesPort;

  @Override
  public Price getApplicablePrice(PriceCriteria priceCriteria) {

    List<Price> prices = this.getPricesPort.findPrices(priceCriteria.brandId(),
        priceCriteria.productId(), priceCriteria.applicationDate());

    return Price.selectApplicable(prices);
  }
  
}
