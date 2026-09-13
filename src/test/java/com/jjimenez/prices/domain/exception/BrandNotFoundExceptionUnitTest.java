package com.jjimenez.prices.domain.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BrandNotFoundExceptionUnitTest {

  @Test
  void whenInstantiatedWithMessageThenReturnCorrectMessage() {
    // Arrange
    String errorMessage = "Brand with id 99 does not exist";

    // Act
    BrandNotFoundException exception = new BrandNotFoundException(errorMessage);

    // Assert
    assertEquals(errorMessage, exception.getMessage());
  }

}
