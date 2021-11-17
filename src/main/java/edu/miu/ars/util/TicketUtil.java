package edu.miu.ars.util;

import java.util.Random;

public class TicketUtil {

    public static String generateNumber() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < 20; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
