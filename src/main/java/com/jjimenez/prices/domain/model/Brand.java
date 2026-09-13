package com.jjimenez.prices.domain.model;

import com.jjimenez.prices.domain.exception.BrandNotFoundException;
import java.util.Arrays;

public enum Brand {
  ZARA(1),
  PULL_AND_BEAR(2);

  private final Integer id;

  Brand(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public static void validateExists(Integer brandId) {
    boolean exists = Arrays.stream(values())
        .anyMatch(brand -> brand.getId().equals(brandId));

    if (!exists) {
      throw new BrandNotFoundException("Brand with id " + brandId + " does not exist");
    }
  }
}
