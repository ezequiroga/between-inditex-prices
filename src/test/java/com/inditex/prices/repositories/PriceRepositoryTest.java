package com.inditex.prices.repositories;

import com.inditex.prices.entities.Price;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
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
public class PriceRepositoryTest {
    
    @Autowired
    private PriceRepository priceRepository;

    public PriceRepositoryTest() {
    }

    /**
     * Test basico para probar la coniguracion.
     */
    @Test
    public void testFindById() {
        Integer id = 1;
        Optional<Price> price = priceRepository.findById(id);
        assertTrue(price.isPresent());
        assertNotNull(price.get().getBrand());
    }

    @Test
    public void testFindPriceNoResults() {
        System.out.println("findPrice - no results");
        Long date = 20210624100000L;
        int productId = 1;
        int brandId = 1;
        List<Price> expResult = priceRepository.findPrice(date, productId, brandId);
        assertTrue(expResult.isEmpty());
    }

    @Test
    public void testFindPrice() {
        System.out.println("findPrice");
        //2020-06-14-16.00.00
        Long date = 20200614160000L;
        int productId = 35455;
        int brandId = 1;
        List<Price> expResult = priceRepository.findPrice(date, productId, brandId);
        assertEquals(2, expResult.size());
    }

}
