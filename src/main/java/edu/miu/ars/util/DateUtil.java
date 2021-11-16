package edu.miu.ars.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (Exception ex) {
            System.out.println("Parsing Exception" + ex.getMessage());
        }
        return null;
    }
}
