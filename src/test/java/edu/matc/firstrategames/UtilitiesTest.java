package edu.matc.firstrategames;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * This is the test for the Epoch method in the Utilities class. It will verify that the year conversion is accurate
 *
 * Created on 11/6/2016.
 * @author Bao
 */

public class UtilitiesTest {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Test
    public void firstOfYearEpochTest() {
        logger.info("In the Epoch Test Method.");

        long longValue = 0;

        Utilities testEpoch = new Utilities();
        longValue = testEpoch.firstOfYearEpoch(2015);

        assertEquals(1420070400000L, longValue);

        logger.info("Epoch Test Complete.");
    }

}