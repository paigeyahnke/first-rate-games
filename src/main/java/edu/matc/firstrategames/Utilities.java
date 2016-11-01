package edu.matc.firstrategames;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

/**
 * Created by Bo on 10/31/2016.
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

}
