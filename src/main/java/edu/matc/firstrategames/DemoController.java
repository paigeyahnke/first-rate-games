package edu.matc.firstrategames;

import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

/**
 * This is the controller for the demo. It accepts the form input and passes the result back.
 * Created on 11/6/16
 * @author Paige Yahnke
 */
@WebServlet(
        name = "demo",
        urlPatterns = { "", "/demo" }
)
public class DemoController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * The do get method for the demo controller. Receives input from form and returns the response.
     * @param request request received
     * @param response response to send
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<String> keys = new ArrayList<>(Utilities.getGenres().values());
        Collections.sort(keys);

        request.setAttribute("genres", keys);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
