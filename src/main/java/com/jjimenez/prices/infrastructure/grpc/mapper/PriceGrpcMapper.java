package com.jjimenez.prices.infrastructure.grpc.mapper;

import com.inditex.precio.infrastructure.inbound.grpc.generated.PrecioResponse;
import com.jjimenez.prices.domain.model.Brand;
import com.jjimenez.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceGrpcMapper {

  @Mapping(source = "brand", target = "brandId")
  @Mapping(source = "startDate", target = "startDate")
  @Mapping(source = "endDate", target = "endDate")
  PrecioResponse toResponse(Price price);

  default Integer mapBrandId(Brand brand) {
    return brand != null ? brand.getId() : 0;
  }

  default String mapLocalDateTimeToString(LocalDateTime dateTime) {
    if (dateTime == null) {
      return "";
    }
    return dateTime.format(DateTimeFormatter.ISO_DATE_TIME);
  }
}
