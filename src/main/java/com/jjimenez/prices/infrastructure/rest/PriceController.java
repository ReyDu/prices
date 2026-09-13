package com.jjimenez.prices.infrastructure.rest;

import com.jjimenez.prices.infrastructure.rest.api.BrandsApi;
import com.jjimenez.prices.infrastructure.rest.dto.PriceResponse;
import java.time.OffsetDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceController implements BrandsApi {

  @Override
  public ResponseEntity<PriceResponse> getApplicablePrice(
      Long brandId,
      Long productId,
      OffsetDateTime applicationDate) {

    return ResponseEntity.ok(new PriceResponse());
  }

}
