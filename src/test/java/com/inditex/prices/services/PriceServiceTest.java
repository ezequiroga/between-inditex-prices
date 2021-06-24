package com.inditex.prices.services;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.entities.Price;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Eze Q.
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PriceServiceTest {

    @Autowired
    private PriceService priceService;

    public PriceServiceTest() {
    }

    @Test
    public void testFindPriceNoData() {
        System.out.println("findPrice - No data");
        Long date = 20210614160000L;
        int productId = 35455;
        int brandId = 1;
        Optional<PriceDTO> result = priceService.findPrice(date, productId, brandId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindPrice() {
        System.out.println("findPrice");
        Long date = 20200614160000L;
        int productId = 35455;
        int brandId = 1;
        Optional<PriceDTO> result = priceService.findPrice(date, productId, brandId);
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
    }

}
