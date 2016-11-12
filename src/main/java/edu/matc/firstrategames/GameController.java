package edu.matc.firstrategames;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Properties;


/**
 * Created by Bo on 10/17/2016.
 */
@Path("/top")
public class GameController {
    private final Logger log = Logger.getLogger(this.getClass());
    Properties properties;
    Properties genres;
    String genre;
    Integer year;
    String responseType;
    String gameResponse;
    Game[] games;

    /**
     * The Java method will process HTTP GET requests and call other methods
     * in order to complete this request
     *
     * @param genre
     * @param year
     * @param responseType
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage (@QueryParam("genre") String genre,
            @QueryParam("year") Integer year,
            @QueryParam("responseType") String responseType) throws IOException {

        //allow for global access
        this.genre = genre;
        this.year = year;
        this.responseType = responseType;

        log.info("Genre: " + genre + " | Year: " + year);
        log.info("Epoch Year Min: " + Utilities.firstOfYearEpoch(year));
        log.info("Epoch Year Max: " + Utilities.firstOfYearEpoch(year + 1));

        //call other methods to complete request
        loadProperties();
        getResponseFromIgdb();
        mapGameResponse();


        if (responseType.equals("json")) {
            // return game json
            return Response.status(200).entity(games[Utilities.getRandomNumber()]).build();
        } else {
            return Response.status(200).entity(games[Utilities.getRandomNumber()].toHTML()).build();
        }

    }


    /**
     * This method loads the values from the properties file
     *
     */
    public void loadProperties() throws IOException {
        // load properties
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("properties.properties")) {
            log.info("Loading properties...");
            properties.load(resourceStream);
        }

        // load genres properties
        genres = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("genres.properties")) {
            log.info("Loading genres properties...");
            genres.load(resourceStream);
        }

    }


    /**
     * This method will get the response from igdb
     *
     */
    public void getResponseFromIgdb() {
        // initialize properties
        String url = properties.getProperty("igdbURL");
        String fields = properties.getProperty("igdbFields");
        String limit = properties.getProperty("igdbLimit");
        String orderBy = properties.getProperty("igdbOrderBy");
        String key = properties.getProperty("igdbXMashapeKey");
        String accept = properties.getProperty("igdbAccept");

        // get the response from igdb
        try {
            log.info("Getting response from igdb.com...");
            log.info("Sending Url: " + url);
            log.info("Accept: " + accept + " | Key: " + key);
            HttpResponse<String> response = Unirest.get(url)
                    // filters
                    .queryString("fields", fields)
                    .queryString("limit", limit)
                    .queryString("order", orderBy)
                    .queryString("filter[genres][eq]", genres.getProperty(genre))
                    .queryString("filter[first_release_date][gte]", Utilities.firstOfYearEpoch(year))
                    .queryString("filter[first_release_date][lt]", Utilities.firstOfYearEpoch(year + 1))
                    // headers
                    .header("X-Mashape-Key", key)
                    .header("Accept", accept)
                    .asString();
            gameResponse = response.getBody();
            log.info("Response: " + gameResponse);
        } catch (UnirestException e) {
            log.error("IGDB API Error", e);
        }
    }

    /**
     * This method will map the JSON response to a java object
     *
     */
    public Game mapGameResponse() throws IOException {
        log.info("Mapping json object to Game object...");
        // object mapper to map response to Game class
        ObjectMapper objectMapper = new ObjectMapper();
        games = objectMapper.readValue(gameResponse, Game[].class);
        return games[0];
    }


}

