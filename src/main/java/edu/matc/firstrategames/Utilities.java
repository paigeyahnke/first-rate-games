package edu.matc.firstrategames;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Random;

/**
 * This is the utilities class that will hold general methods.
 * Created on 10/31/2016.
 * @author Bo
 */
public class Utilities {

    /**
     * This method converts a year value into epoch format
     *
     * @param year The year to be converted
     * @return year in epoch format
     */
    public static long firstOfYearEpoch(int year) {
        return LocalDate.of(year, Month.JANUARY, 1)
                .atStartOfDay(ZoneId.of("Z"))
                .toEpochSecond() * 1000;
    }

    /**
     * Returns a random number
     * @return random number
     */
    public static int getRandomNumber() {

        Random r = new Random();
        return r.nextInt(10);

    }

}
