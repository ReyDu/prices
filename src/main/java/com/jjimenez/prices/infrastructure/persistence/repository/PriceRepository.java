package com.jjimenez.prices.infrastructure.persistence.repository;

import com.jjimenez.prices.infrastructure.persistence.entity.PriceEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

  @Query("""
          SELECT p
          FROM PriceEntity p
          WHERE p.brandId = :brandId
            AND p.productId = :productId
            AND :applicationDate BETWEEN p.startDate AND p.endDate
      """)
  List<PriceEntity> findCandidates(
      @Param("brandId") Long brandId,
      @Param("productId") Long productId,
      @Param("applicationDate") LocalDateTime applicationDate
  );
}
