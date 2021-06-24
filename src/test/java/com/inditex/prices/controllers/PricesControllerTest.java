package com.inditex.prices.controllers;

import com.inditex.prices.dtos.PriceDTO;
import com.inditex.prices.entities.Brand;
import com.inditex.prices.entities.Price;
import com.inditex.prices.services.PriceService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 *
 * @author Eze Q.
 */
//@SpringBootTest
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = PricesController.class)
public class PricesControllerTest {
    
    @MockBean
    private PriceService priceService;

    @Autowired
    private WebTestClient webClient;

    public PricesControllerTest() {
    }

    @Test
    public void testGetPricesIncorrectDateFormat() {
        System.out.println("getPrices - IncorrectDateFormat");

        webClient.get().uri(
                uriBuilder -> uriBuilder
                        .path("/prices/")
                        .queryParam("date", "2020-12-3123.59.59")
                        .queryParam("productId", "35455")
                        .queryParam("brandId", "1")
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testGetPricesNoData() {
        System.out.println("getPrices - No data");
        
        when(priceService.findPrice(any(Long.class), any(Integer.class), any(Integer.class))).thenReturn(Optional.empty());

        webClient.get().uri(
                uriBuilder -> uriBuilder
                        .path("/prices/")
                        .queryParam("date", "2020-12-31-23.59.59")
                        .queryParam("productId", "35455")
                        .queryParam("brandId", "1")
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetPrices() {
        System.out.println("getPrices");
        
        Brand brand = new Brand();
        brand.setId(1);
        brand.setDesc("ZARA");
        
        PriceDTO price = new PriceDTO();
        price.setPriceList(2);
        price.setBrandId(1);
        price.setStartDate("2020-06-14-15.00.00");
        price.setEndDate("2020-06-14-18.30.00");
        price.setProductId(35455);
        price.setPrice(BigDecimal.valueOf(35.50));
        
        when(priceService.findPrice(any(Long.class), any(Integer.class), any(Integer.class))).thenReturn(Optional.of(price));

        webClient.get().uri(
                uriBuilder -> uriBuilder
                        .path("/prices/")
                        .queryParam("date", "2020-06-14-16.00.00")
                        .queryParam("productId", "35455")
                        .queryParam("brandId", "1")
                        .build())
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectBody().equals(price);
    }

}
