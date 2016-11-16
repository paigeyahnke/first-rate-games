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


    /**
     * Returns a non-empty collection with classes, that must be included in the published JAX-RS application
     * @return collection of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet classes = new HashSet<Class<?>>();
        classes.add(GameController.class);
        return classes;
    }
}