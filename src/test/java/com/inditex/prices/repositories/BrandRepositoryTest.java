package com.inditex.prices.repositories;

import com.inditex.prices.entities.Brand;
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
public class BrandRepositoryTest {
    
    @Autowired
    private BrandRepository brandRepository;
    
    public BrandRepositoryTest() {
    }

    /**
     * Test basico para probar la coniguracion.
     */
    @Test
    public void testFindById() {
        Integer id = 1;
        Optional<Brand> brand = brandRepository.findById(id);
        assertTrue(brand.isPresent());
        assertEquals("ZARA", brand.get().getDesc());
    }
    
}
