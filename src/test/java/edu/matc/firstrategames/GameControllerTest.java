package edu.matc.firstrategames;

import org.apache.log4j.Logger;
import org.junit.Test;

import javax.ws.rs.Produces;
import javax.xml.ws.Response;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.client.*;

/**
 * Created by bvue0 on 11/6/2016.
 */
public class GameControllerTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void testGetResponseFromIgdb() throws IOException {
        logger.info("In method.");

        String response = "";

        GameController testGame = new GameController();
        testGame.getMessage("Adventure", 2015, "json");

        response = (String)testGame.mapGameResponse().getName();

        assertEquals("The Witcher 3: Wild Hunt", response);

    }

}
