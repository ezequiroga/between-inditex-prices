package com.inditex.prices.services;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.exceptions.DatePriceFormatException;
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
    public void testFindPriceNoData() throws DatePriceFormatException {
        System.out.println("findPrice - No data");
        Optional<String> date = Optional.of("2021-06-14-16.00.00");
        int productId = 35455;
        int brandId = 1;
        Optional<PriceDTO> result = priceService.findPrice(date, productId, brandId);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindPrice() throws DatePriceFormatException {
        System.out.println("findPrice");
        Optional<String> date = Optional.of("2020-06-14-16.00.00");
        int productId = 35455;
        int brandId = 1;
        Optional<PriceDTO> result = priceService.findPrice(date, productId, brandId);
        assertTrue(result.isPresent());
        assertEquals(2, result.get().getPriceList());
    }

}
