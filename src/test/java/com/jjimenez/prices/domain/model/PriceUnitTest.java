package com.jjimenez.prices.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jjimenez.prices.domain.exception.PriceNotFoundException;
import com.jjimenez.prices.fixtures.PriceFixtures;
import java.util.List;
import org.junit.jupiter.api.Test;

class PriceUnitTest {

  @Test
  void whenCandidatesIsEmptyThenThrowPriceNotFoundException() {
    PriceNotFoundException exception = assertThrows(
        PriceNotFoundException.class,
        () -> Price.selectApplicable(List.of())
    );
    assertEquals(Price.NO_PRICE_FOUND_MSG, exception.getMessage());
  }

  @Test
  void whenMultipleCandidatesAreProvidedThenReturnPriceWithHighestPriority() {
    // Arrange
    Price lowPriorityPrice = PriceFixtures.createValidPrice().toBuilder()
        .priority(0)
        .build();

    Price highPriorityPrice = PriceFixtures.createValidPrice().toBuilder()
        .priority(1)
        .priceList(2)
        .build();

    List<Price> candidates = List.of(lowPriorityPrice, highPriorityPrice);

    // Act
    Price result = Price.selectApplicable(candidates);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.priority());
    assertEquals(2, result.priceList());
  }
}
