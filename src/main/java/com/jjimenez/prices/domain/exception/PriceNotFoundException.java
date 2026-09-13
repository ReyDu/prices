package com.jjimenez.prices.domain.exception;

public class PriceNotFoundException extends RuntimeException {

  public PriceNotFoundException(String message) {
    super(message);
  }
}
