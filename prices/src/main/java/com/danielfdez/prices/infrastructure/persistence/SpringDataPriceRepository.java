package com.danielfdez.prices.infrastructure.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataPriceRepository extends CrudRepository<PriceEntity, Long> {

    @Query("SELECT p FROM PriceEntity p WHERE p.productId = :productId AND p.brandId = :brandId AND :date BETWEEN " +
            "p.startDate AND p.endDate")
    List<PriceEntity> findApplicablePrices(Long productId, Long brandId, LocalDateTime date);
}

