package com.inditex.prices.utils;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Eze Q.
 */
public class Utils {
    
    public static final String DATE_FORMAT = "uuuu-MM-dd-HH.mm.ss";

    public static boolean validDate(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
        } catch (Exception ex){
            return false;
        }
        return true;
    }

    public static Long dateApiToDateDB(String date) {
        return Long.parseLong(date.replaceAll("-", "").replaceAll("\\.", ""));
    }

    public static Long generateInstantDateDB() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        ZonedDateTime localTime = ZonedDateTime.now();
        return dateApiToDateDB(dateTimeFormatter.format(localTime));
    }

    public static String dateDBToDateApi(Long date) {
        String sDate = String.valueOf(date);
        StringBuilder sb = new StringBuilder();
        sb.append(sDate.substring(0, 4)).append("-");
        sb.append(sDate.substring(4, 6)).append("-");
        sb.append(sDate.substring(6, 8)).append("-");
        sb.append(sDate.substring(8, 10)).append(".");
        sb.append(sDate.substring(10, 12)).append(".");
        sb.append(sDate.substring(12));
        return sb.toString();
    }
}
