package com.inditex.prices.utils;

import static com.inditex.prices.utils.Utils.dateApiToDateDB;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Eze Q.
 */
public class UtilsTest {
    
    public UtilsTest() {
    }

    @Test
    public void testValidDate() {
        System.out.println("validDate");
        String date = "2020-12-31-23.59.59";
        boolean expResult = true;
        boolean result = Utils.validDate(date);
        assertEquals(expResult, result);
    }

    @Test
    public void testValidDateFalse() {
        System.out.println("validDate");
        String date = "2020-12-3123.59.59";
        boolean expResult = false;
        boolean result = Utils.validDate(date);
        assertEquals(expResult, result);
    }

    @Test
    public void testDateApiToDateDB() {
        System.out.println("dateApiToDateDB");
        String date = "2020-12-31-23.59.59";
        Long expResult = 20201231235959L;
        Long result = Utils.dateApiToDateDB(date);
        assertEquals(expResult, result);
    }

    @Test
    public void testGenerateInstantDateDB() {
        System.out.println("generateInstantDateDB");
        
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH.mm.ss");
        ZonedDateTime localTime = ZonedDateTime.now();        
        Long expResult = Long.parseLong(dateTimeFormatter.format(localTime).replaceAll("-", "").replaceAll("\\.", ""));
        Long result = Utils.generateInstantDateDB();
        assertEquals(expResult, result);
    }

    @Test
    public void testDateDBToDateApi() {
        System.out.println("dateDBToDateApi");
        Long date = 20201231235959L;
        String expResult = "2020-12-31-23.59.59";
        String result = Utils.dateDBToDateApi(date);
        assertEquals(expResult, result);
    }
    
}
