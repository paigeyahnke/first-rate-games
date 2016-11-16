package edu.matc.firstrategames;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


/**
 *
 * This is the test for the Game Controller class. It will verify that the IGDB API is returning a value
 *
 * Created on 11/6/2016.
 * @author Bao
 */

public class GameControllerTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * Verifies that IDGB is sending a response back.
     */
    @Test
    public void testGetResponseFromIgdb() throws IOException {
        logger.info("Testing IDGB response.");

        GameController testGame = new GameController();
        String response = testGame.getResponseFromIgdb("Adventure", 2015, "json");

        assertNotNull("No response", response);
    }

    /**
     * Verify game attributes are accessible after mapping
     */
    @Test
    public void testGameMapper() throws IOException {
        logger.info("Testing IDGB response.");

        GameController testGame = new GameController();
        String response = testGame.getResponseFromIgdb("Adventure", 2015, "json");

        Game game = testGame.mapGameResponse(response)[0];
        assertNotNull("Game is missing name", game.getName());
        assertNotNull("Game is missing genres", game.getGenres());
        assertNotNull("Game is missing release year", game.getReleaseYear());

    }

}
