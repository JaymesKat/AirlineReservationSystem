package edu.miu.ars.util;

import edu.miu.ars.domain.AppUser;
import edu.miu.ars.service.AppUserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Component
public class AppUtil {

    private final AppUserService appUserService;

    public AppUtil(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    public AppUser getFromAuthentication(Authentication authentication) {
        return appUserService.getByEmail(authentication.getName());
    }

    public String generateReservationCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }

    public String generateTicketNumber() {
        char[] chars = "abcdefghijklmnop1234567890qrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder(20);
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString().toUpperCase();
    }

    public int dateDifferenceInDay(Date date1, Date date2) {
        // getting milliseconds for both dates
        long date1InMs = date1.getTime();
        long date2InMs = date2.getTime();
        // getting the diff between two dates.
        long timeDiff = 0;
        timeDiff = date1InMs - date2InMs;
        // converting diff into days
        int daysDiff = (int) (timeDiff / (1000 * 60 * 60 * 24));

        return daysDiff;
    }
}
