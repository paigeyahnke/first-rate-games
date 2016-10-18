package edu.matc.firstrategames;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Bo on 10/17/2016.
 */
@Path("/game")
public class GameController {
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/top")
    public Response getMessage() {
        return Response.status(200).entity(new Game()).build();
    }
}
