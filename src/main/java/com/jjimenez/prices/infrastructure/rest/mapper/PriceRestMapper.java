package com.jjimenez.prices.infrastructure.rest.mapper;

import com.jjimenez.prices.domain.model.Brand;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.rest.dto.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

  @Mapping(source = "brand", target = "brandId")
  PriceResponse toResponse(Price price);

  default Integer mapBrandId(Brand brand) {
    return brand != null ? brand.getId() : null;
  }

}
