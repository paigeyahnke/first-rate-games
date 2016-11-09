package edu.matc.firstrategames;

import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bvue0 on 11/6/2016.
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