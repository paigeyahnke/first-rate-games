package edu.matc.firstrategames;

import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.*;

/**
 * This is the utilities class that will hold general methods.
 * Created on 10/31/2016.
 * @author Bo
 */
public class Utilities {
    private static final Logger log = Logger.getLogger(Utilities.class);

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


    /**
     * This will load the genre property from one of the properties files
     *
     * @return the video game genres
     */
    public static TreeMap<Integer, String> getGenres() {
        Properties genreProperties = loadProperties("genres.properties");

        TreeMap<Integer, String> genres = new TreeMap<>();

        for(Map.Entry<Object, Object> pair : genreProperties.entrySet()) {
            genres.put(Integer.parseInt((String)pair.getValue()), (String) pair.getKey());
        }

        return genres;
    }


    /**
     * This method will load the values from the properties files
     *
     * @param propertiesPath the path for the properties file
     * @return the properties values
     */
    public static Properties loadProperties(String propertiesPath) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(propertiesPath)) {
            log.info("Loading properties...");
            properties.load(resourceStream);
        } catch (IOException exception) {
            log.error(exception.getStackTrace());
        }

        return properties;
    }

}
