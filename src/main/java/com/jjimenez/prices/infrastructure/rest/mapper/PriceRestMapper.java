package com.jjimenez.prices.infrastructure.rest.mapper;

import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.rest.dto.PriceResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceRestMapper {

  PriceResponse toResponse(Price price);
}
