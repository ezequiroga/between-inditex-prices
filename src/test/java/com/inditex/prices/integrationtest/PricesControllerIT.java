package com.inditex.prices.integrationtest;

import com.inditex.prices.entities.Brand;
import com.inditex.prices.entities.Price;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Eze Q.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class PricesControllerIT {
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private String uri;
    
    @BeforeEach
    private void setUp(){
        StringBuilder sb = new StringBuilder();
        sb.append("http://localhost:").append(port);
        uri = sb.toString();
    }

    public PricesControllerIT() {
    }

    @Test
    public void testGetPricesIncorrectDateFormat() {
        System.out.println("getPrices - IncorrectDateFormat");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-12-3123.59.59");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<Price> re = restTemplate.getForEntity(sb.toString(), Price.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    public void testGetPricesNoData() {
        System.out.println("getPrices - No data");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2021-12-31-23.59.59");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<Price> re = restTemplate.getForEntity(sb.toString(), Price.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetPrices() {
        System.out.println("getPrices");
        
        Brand brand = new Brand();
        brand.setId(1);
        brand.setDesc("ZARA");
        
        Price price = new Price();
        price.setId(2);
        price.setBrand(brand);
        price.setStartDate(20200614150000L);
        price.setEndDate(20200614183000L);
        price.setProductId(35455);
        price.setPriority(1);
        price.setPrice(BigDecimal.valueOf(25.45));
        price.setCurr("EUR");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-14-16.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<Price> re = restTemplate.getForEntity(sb.toString(), Price.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(2, re.getBody().getId());
        assertEquals(BigDecimal.valueOf(25.45), re.getBody().getPrice());
    }

}
