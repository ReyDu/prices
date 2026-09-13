package com.jjimenez.prices.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jjimenez.prices.application.port.GetPricesPort;
import com.jjimenez.prices.application.usecase.PriceCriteria;
import com.jjimenez.prices.domain.exception.PriceNotFoundException;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.fixtures.PriceCriteriaFixtures;
import com.jjimenez.prices.fixtures.PriceFixtures;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceUnitTest {

  @Mock
  private GetPricesPort getPricesPort;

  @InjectMocks
  private GetApplicablePrice getApplicablePrice;

  @Test
  void whenDataIsFoundThenReturnPrice() {
    PriceCriteria criteria = PriceCriteriaFixtures.createValidCriteria();
    Price expectedPrice = PriceFixtures.createValidPrice();

    when(getPricesPort.findPrices(criteria.brandId(), criteria.productId(),
        criteria.applicationDate()))
        .thenReturn(List.of(expectedPrice));

    Price result = getApplicablePrice.getPrice(criteria);

    assertNotNull(result);
    assertEquals(expectedPrice.productId(), result.productId());
    assertEquals(expectedPrice.brand(), result.brand());
    verify(getPricesPort, times(1)).findPrices(criteria.brandId(), criteria.productId(),
        criteria.applicationDate());
  }

  @Test
  void whenNoPriceIsFoundThenThrowPriceNotFoundException() {
    PriceCriteria criteria = PriceCriteriaFixtures.createNotFoundCriteria();

    when(getPricesPort.findPrices(criteria.brandId(), criteria.productId(),
        criteria.applicationDate()))
        .thenReturn(Collections.emptyList());

    assertThrows(PriceNotFoundException.class, () ->
        getApplicablePrice.getPrice(criteria)
    );
  }
}