package com.danielfdez.prices.domain.repository;

import com.danielfdez.prices.domain.model.Price;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository {
    List<Price> findByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime date);
}
