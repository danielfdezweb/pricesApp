package com.danielfdez.prices.infrastructure.controller;

import com.danielfdez.prices.application.usecase.GetApplicablePriceUseCase;
import com.danielfdez.prices.domain.model.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/prices")
public class PriceController {

    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public Price getPrice(@RequestParam Long productId,consultar
                          @RequestParam Long brandId,
                          @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        return useCase.execute(productId, brandId, date);
    }
}
