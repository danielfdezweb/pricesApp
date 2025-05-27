package com.danielfdez.prices.application.usecase;

import com.danielfdez.prices.domain.exception.PriceNotFoundException;
import com.danielfdez.prices.domain.model.Price;
import com.danielfdez.prices.domain.repository.PriceRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class GetApplicablePriceUseCase {
    private final PriceRepository priceRepository;

    public GetApplicablePriceUseCase(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Price execute(Long productId, Long brandId, LocalDateTime date) {
        return priceRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date).stream()
                .max(Comparator.comparing(Price::getPriority))
                .orElseThrow(() -> new PriceNotFoundException("No applicable price found for productId: "
                        + productId + ", brandId: " + brandId + ", date: " + date));
    }
}
