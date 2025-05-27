package com.danielfdez.prices.infrastructure.persistence;

import com.danielfdez.prices.domain.model.Price;
import com.danielfdez.prices.domain.repository.PriceRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataPriceRepository jpaRepo;

    public JpaPriceRepository(SpringDataPriceRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Price> findByProductIdAndBrandIdAndDate(Long productId, Long brandId, LocalDateTime date) {
        return jpaRepo.findApplicablePrices(productId, brandId, date).stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Price toDomain(PriceEntity entity) {
        Price price = new Price();
        price.setBrandId(entity.getBrandId());
        price.setStartDate(entity.getStartDate());
        price.setEndDate(entity.getEndDate());
        price.setPriceList(entity.getPriceList());
        price.setProductId(entity.getProductId());
        price.setPriority(entity.getPriority());
        price.setPrice(entity.getPrice());
        price.setCurrency(entity.getCurrency());
        return price;
    }
}
