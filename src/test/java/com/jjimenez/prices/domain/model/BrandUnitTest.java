package com.jjimenez.prices.domain.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jjimenez.prices.domain.exception.BrandNotFoundException;
import org.junit.jupiter.api.Test;

class BrandUnitTest {

  @Test
  void whenExistingIdProvidedThenReturnBrand() {
    Brand brand = Brand.fromId(1);
    assertEquals(Brand.ZARA, brand);
  }

  @Test
  void whenNonExistingIdProvidedInFromIdThenThrowBrandNotFoundException() {
    BrandNotFoundException exception = assertThrows(
        BrandNotFoundException.class,
        () -> Brand.fromId(99)
    );
    assertEquals("Brand with id 99 does not exist", exception.getMessage());
  }

  @Test
  void whenExistingBrandIdProvidedThenValidateSuccessfully() {
    assertDoesNotThrow(() -> Brand.validateExists(1));
  }

  @Test
  void whenNonExistingBrandIdProvidedInValidateExistsThenThrowBrandNotFoundException() {
    BrandNotFoundException exception = assertThrows(
        BrandNotFoundException.class,
        () -> Brand.validateExists(99)
    );
    assertEquals("Brand with id 99 does not exist", exception.getMessage());
  }

}
