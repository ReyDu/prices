package com.jjimenez.prices.application.usecase;

import com.jjimenez.prices.domain.model.Price;

public interface GetApplicablePriceUseCase {

  public Price getPrice(PriceCriteria priceCriteria);

}
