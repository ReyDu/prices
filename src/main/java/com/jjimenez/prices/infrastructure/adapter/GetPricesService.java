package com.jjimenez.prices.infrastructure.adapter;

import com.jjimenez.prices.application.port.GetPricesPort;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.persistence.entity.PriceEntity;
import com.jjimenez.prices.infrastructure.persistence.mapper.PriceMapper;
import com.jjimenez.prices.infrastructure.persistence.repository.PriceRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPricesService implements GetPricesPort {

  private final PriceRepository priceRepository;

  private final PriceMapper priceMapper;

  @Override
  public List<Price> findPrices(Integer brandId, Long productId, LocalDateTime applicationDate) {
    List<PriceEntity> priceEntityList = priceRepository.findCandidates(
        brandId, productId, applicationDate);

    return this.priceMapper.toDomainList(priceEntityList);
  }

}
