package com.jjimenez.prices.application;

import com.jjimenez.prices.application.port.GetPricesPort;
import com.jjimenez.prices.application.usecase.GetApplicablePriceUseCase;
import com.jjimenez.prices.application.usecase.PriceCriteria;
import com.jjimenez.prices.domain.model.Brand;
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

    Integer brandId = priceCriteria.brandId();

    Brand.validateExists(brandId);

    List<Price> prices = this.getPricesPort.findPrices(brandId,
        priceCriteria.productId(), priceCriteria.applicationDate());

    return Price.selectApplicable(prices);
  }

}
