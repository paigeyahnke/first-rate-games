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
import java.util.Properties;


/**
 * This class controls the request and response of the game. It will process the GET request by grabbing the user's
 * entry of year, genre, and response type. It will load those values along with info from the properties files
 * and submit that to IGDB's API. IGDB will return JSON which will then map to the Game object and return output
 * to the UI.
 * Created 10/17/2016.
 * @author Bo
 */
@Path("/top")
public class GameController {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * The Java method will process HTTP GET requests and call other methods
     * in order to complete this request. This will map the JSON response to the Game class.
     *
     * @param genre the user entered genre
     * @param year the user entered year
     * @param responseType the response typer the user prefers
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage (@QueryParam("genre") String genre,
                                @QueryParam("year") Integer year,
                                @QueryParam("responseType") String responseType) throws IOException {

        log.info("Genre: " + genre + " | Year: " + year);
        log.info("Epoch Year Min: " + Utilities.firstOfYearEpoch(year));
        log.info("Epoch Year Max: " + Utilities.firstOfYearEpoch(year + 1));

        String response = getResponseFromIgdb(genre, year, responseType);

        if (response != null) {
            Game[] games = mapGameResponse(response);

            if (responseType.equals("json")) {
                // return game json
                return Response.status(200).entity(games[Utilities.getRandomNumber()]).build();
            } else {
                return Response.status(200).entity(games[Utilities.getRandomNumber()].toHTML()).build();
            }
        } else {
            return Response.status(500).entity("{'error' : 'Server error. Could not complete request.'}").build();
        }

    }

    /**
     * This method will get the response from IGDB by sending info from the properties file
     * and the user request to the IGDB API
     *
     */
    public String getResponseFromIgdb(String genre, Integer year, String responseType) {
        Properties properties = Utilities.loadProperties("properties.properties");
        Properties genres = Utilities.loadProperties("genres.properties");

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
            String gameResponse = response.getBody();
            log.info("Response: " + gameResponse);
            return gameResponse;
        } catch (UnirestException e) {
            log.error("IGDB API Error", e);
            return null;
        }
    }

    /**
     * This method will map the JSON response to a java object array
     *
     * @return the array of mapped game objects
     * @param gameResponse the response from the IGDB API
     *
     */
    public Game[] mapGameResponse(String gameResponse) throws IOException {
        log.info("Mapping json object to Game object...");
        // object mapper to map response to Game class
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(gameResponse, Game[].class);
    }


}

