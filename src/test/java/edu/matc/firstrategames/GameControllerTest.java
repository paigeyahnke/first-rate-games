package edu.matc.firstrategames;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Created by bvue0 on 11/6/2016.
 */
public class GameControllerTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void testGetResponseFromIgdb() throws IOException {
        logger.info("Testing IDGB response.");

        GameController testGame = new GameController();
        testGame.getMessage("Adventure", 2015, "json");


        Game game = testGame.mapGameResponse(testGame.getResponseFromIgdb())[0];

        assertNotNull("No response", game);
    }

}
