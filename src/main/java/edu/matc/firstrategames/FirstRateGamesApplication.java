package edu.matc.firstrategames;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * The java class declares root resource and provider classes and defines the base URI for all resource URIs
 */


@ApplicationPath("game")
public class FirstRateGamesApplication extends Application {

    //The method returns a non-empty collection with classes, that must be included in the published JAX-RS application
    @Override
    public Set<Class<?>> getClasses() {
        HashSet h = new HashSet<Class<?>>();
        h.add(GameController.class);
        return h;
    }
}