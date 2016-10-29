package edu.matc.firstrategames;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    private final Logger logger = Logger.getLogger(this.getClass());

    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public Response getMessage(@QueryParam("genre") String genre,
                               @QueryParam("year") Integer year) throws IOException {
        logger.info("Genre: " + genre);
        logger.info("Year: " + year);
        // get list of json objects from file
        ObjectMapper objectMapper = new ObjectMapper();

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("sampleData.json");

        Game[] games = objectMapper.readValue(is, Game[].class);
        List<Game> filteredGames = new ArrayList<Game>(Arrays.asList(games));

        // loop through games, filtering out "non-matches"
        for (Game game : games) {
            if (genre != null && !game.getGenre().equals(genre)) {
                filteredGames.remove(game);
            }
            if (year != null && game.getYear() != year) {
                filteredGames.remove(game);
            }
        }

        String[] testArray = new String[filteredGames.size()];
        for (int i = 0; i < filteredGames.size(); i++) {
            testArray[i] = filteredGames.get(i).toString();
        }
        String result = String.join(",", testArray);
        return Response.status(200).entity(result).build();
    }
}

