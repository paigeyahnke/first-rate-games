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
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Bo on 10/17/2016.
 */
@Path("/top")
public class GameController {
    private final Logger log = Logger.getLogger(this.getClass());

    // TODO use properties file to clean up hard coded data
    // The Java method will process HTTP GET requests
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@QueryParam("genre") String genre,
                               @QueryParam("year") Integer year) throws IOException {
        log.info("Genre: " + genre);
        log.info("Year: " + year);
        log.info("Epoch Year Min: " + Utilities.firstOfYearEpoch(year));
        log.info("Epoch Year Max: " + Utilities.firstOfYearEpoch(year + 1));

        // api base url
        String url = "https://igdbcom-internet-game-database-v1.p.mashape.com/games/";
        // query string needed for igdb api, change limit for more results
        String queryString = "?fields=name,genres,rating,first_release_date&limit=1&offset=0&order=rating:desc";
        // declare additional filters from params
        String genreFilter = "";
        String yearFilterMin = "";
        String yearFilterMax = "";
        // response from igdb
        String gameResponse = "";

        // set additional filters from params
        if (genre != null) {
            genreFilter = "&filter[genres][eq]=" + genre;
        }
        if (year != null) {
            yearFilterMin = "&filter[first_release_date][gte]=" + Utilities.firstOfYearEpoch(year);
            yearFilterMax = "&filter[first_release_date][lt]=" + Utilities.firstOfYearEpoch(year + 1);
        }

        // get the response from igdb
        try {
            HttpResponse<String> response = Unirest.get(url + queryString + genreFilter + yearFilterMin + yearFilterMax)
                    .header("X-Mashape-Key", "JtIfKzpDYlmshIqjqfbPp0v9uOngp1Vi6W8jsnOgwGGBqCadVb")
                    .header("Accept", "application/json")
                    .asString();
            gameResponse = response.getBody();
        } catch (UnirestException e) {
            log.error("API Error", e);
        }

        // object mapper to map response to Game class
        ObjectMapper objectMapper = new ObjectMapper();
        Game[] game = objectMapper.readValue(gameResponse, Game[].class);

        // return game json
        return Response.status(200).entity(game).build();
    }
}

