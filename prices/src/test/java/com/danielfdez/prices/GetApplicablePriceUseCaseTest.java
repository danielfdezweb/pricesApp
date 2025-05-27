package com.danielfdez.prices;

import com.danielfdez.prices.application.usecase.GetApplicablePriceUseCase;
import com.danielfdez.prices.domain.exception.PriceNotFoundException;
import com.danielfdez.prices.domain.model.Price;
import com.danielfdez.prices.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetApplicablePriceUseCaseTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetApplicablePriceUseCase useCase;

    @Test
    void returnsHighestPriorityPrice() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0);

        Price lowPriority = new Price(
                1L,
                date.minusHours(1),
                date.plusHours(1),
                1,
                productId,
                0,
                new BigDecimal("25.00"),
                "EUR"
        );

        Price highPriority = new Price(
                2L,
                date.minusHours(1),
                date.plusHours(1),
                2,
                productId,
                1,
                new BigDecimal("35.50"),
                "EUR"
        );

        when(priceRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date))
                .thenReturn(List.of(lowPriority, highPriority));

        Price result = useCase.execute(productId, brandId, date);

        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals(1, result.getPriority());
    }

    @Test
    void throwsExceptionWhenNoPriceFound() {
        Long productId = 35455L;
        Long brandId = 1L;
        LocalDateTime date = LocalDateTime.of(2020, 1, 1, 0, 0);

        when(priceRepository.findByProductIdAndBrandIdAndDate(productId, brandId, date))
                .thenReturn(Collections.emptyList());

        assertThrows(PriceNotFoundException.class, () -> useCase.execute(productId, brandId, date));
    }
}
