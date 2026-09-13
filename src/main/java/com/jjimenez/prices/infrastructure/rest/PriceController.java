package com.jjimenez.prices.infrastructure.rest;

import com.jjimenez.prices.application.usecase.GetApplicablePriceUseCase;
import com.jjimenez.prices.application.usecase.PriceCriteria;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.rest.api.BrandsApi;
import com.jjimenez.prices.infrastructure.rest.dto.PriceResponse;
import com.jjimenez.prices.infrastructure.rest.mapper.PriceRestMapper;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PriceController implements BrandsApi {

  private final GetApplicablePriceUseCase getApplicablePriceUseCase;

  private final PriceRestMapper priceRestMapper;

  @Override
  public ResponseEntity<PriceResponse> getApplicablePrice(
      Integer brandId,
      Long productId,
      LocalDateTime applicationDate) {

    PriceCriteria priceCriteria = PriceCriteria.builder()
        .brandId(brandId)
        .productId(productId)
        .applicationDate(applicationDate)
        .build();

    Price price = this.getApplicablePriceUseCase.getPrice(
        priceCriteria);

    PriceResponse response = this.priceRestMapper.toResponse(price);

    return ResponseEntity.ok(response);
  }

}
