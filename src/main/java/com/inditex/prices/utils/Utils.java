package com.inditex.prices.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

/**
 *
 * @author Eze Q.
 */
public class Utils {
    
    public static boolean validDate(String date){
        String regex = "^\\d{4}-\\d{2}-\\d{2}-\\d{2}\\.\\d{2}\\.\\d{2}$";
        Pattern pat = Pattern.compile(regex);
        return pat.matcher(date).matches();
    }

    public static Long dateApiToDateDB(String date) {
        return Long.parseLong(date.replaceAll("-", "").replaceAll("\\.", ""));
    }
    
    public static Long generateInstantDateDB(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd-HH.mm.ss");
        ZonedDateTime localTime = ZonedDateTime.now();
        return dateApiToDateDB(dateTimeFormatter.format(localTime));
    }
    
    public static String dateDBToDateApi(Long date){
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
