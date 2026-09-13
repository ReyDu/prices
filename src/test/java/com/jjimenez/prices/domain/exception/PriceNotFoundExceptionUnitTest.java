package com.jjimenez.prices.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PriceNotFoundExceptionUnitTest {

  @Test
  void whenInstantiatedWithMessageThenReturnCorrectMessage() {
    // Arrange
    String errorMessage = "Price not found for the given criteria";

    // Act
    PriceNotFoundException exception = new PriceNotFoundException(errorMessage);

    // Assert
    assertEquals(errorMessage, exception.getMessage());
  }

}
