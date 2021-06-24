package com.inditex.prices.integrationtest;

import com.inditex.prices.dtos.PriceDTO;
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

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
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

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void testGetPricesMissingParams() {
        System.out.println("getPrices - Missing params");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2021-12-31-23.59.59");
        sb.append("&");
        sb.append("productId=35455");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetPrices_Test1() {
        System.out.println("getPrices - Test 1");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-14-10.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(1, re.getBody().getPriceList());
        assertEquals(BigDecimal.valueOf(35.50).setScale(2), re.getBody().getPrice());
    }

    @Test
    public void testGetPrices_Test2() {
        System.out.println("getPrices - Test 2");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-14-16.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(2, re.getBody().getPriceList());
        assertEquals(BigDecimal.valueOf(25.45).setScale(2), re.getBody().getPrice());
    }

    @Test
    public void testGetPrices_Test3() {
        System.out.println("getPrices - Test 3");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-14-21.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(1, re.getBody().getPriceList());
        assertEquals(BigDecimal.valueOf(35.50).setScale(2), re.getBody().getPrice());
    }

    @Test
    public void testGetPrices_Test4() {
        System.out.println("getPrices - Test 4");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-15-10.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(3, re.getBody().getPriceList());
        assertEquals(BigDecimal.valueOf(30.50).setScale(2), re.getBody().getPrice());
    }

    @Test
    public void testGetPrices_Test5() {
        System.out.println("getPrices - Test 5");
        
        StringBuilder sb = new StringBuilder();
        sb.append(uri).append("/prices/");
        sb.append("?");
        sb.append("date=2020-06-16-21.00.00");
        sb.append("&");
        sb.append("productId=35455");
        sb.append("&");
        sb.append("brandId=1");

        ResponseEntity<PriceDTO> re = restTemplate.getForEntity(sb.toString(), PriceDTO.class);
        
        assertEquals(re.getStatusCode(),HttpStatus.OK);
        assertEquals(4, re.getBody().getPriceList());
        assertEquals(BigDecimal.valueOf(38.95).setScale(2), re.getBody().getPrice());
    }

}
