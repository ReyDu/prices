package com.jjimenez.prices.infrastructure.persistence.mapper;

import com.jjimenez.prices.domain.model.Brand;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.persistence.entity.PriceEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  @Mapping(source = "brandId", target = "brand")
  Price toDomain(PriceEntity entity);

  List<Price> toDomainList(List<PriceEntity> entities);

  default Brand mapBrand(Integer brandId) {
    return brandId != null ? Brand.fromId(brandId) : null;
  }

}
