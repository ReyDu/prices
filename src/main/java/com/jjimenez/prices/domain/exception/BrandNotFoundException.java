package com.jjimenez.prices.domain.exception;

public class BrandNotFoundException extends RuntimeException {

  public BrandNotFoundException(String message) {
    super(message);
  }
}
