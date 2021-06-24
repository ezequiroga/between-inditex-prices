package com.inditex.prices.services;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.entities.Price;
import com.inditex.prices.repositories.PriceRepository;
import com.inditex.prices.utils.Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Eze Q.
 */
@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public Optional<PriceDTO> findPrice(Long date, int productId, int brandId) {
        return priceRepository
                .findPrice(date, productId, brandId)
                .stream()
                .findFirst()
                .map(p -> new PriceDTO(
                    p.getProductId(),
                    p.getBrand().getId(),
                    p.getId(),
                    Utils.dateDBToDateApi(p.getStartDate()),
                    Utils.dateDBToDateApi(p.getEndDate()),
                    p.getPrice())
                );
    }

}
