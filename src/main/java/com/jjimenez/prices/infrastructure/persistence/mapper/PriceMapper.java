package com.jjimenez.prices.infrastructure.persistence.mapper;

import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.persistence.entity.PriceEntity;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceMapper {

  List<Price> toDomainList(List<PriceEntity> entities);

}
