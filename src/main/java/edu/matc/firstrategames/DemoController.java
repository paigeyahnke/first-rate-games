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
 * Created by paige on 11/6/16.
 */
@WebServlet(
        name = "demo",
        urlPatterns = { "", "/demo" }
)
public class DemoController extends HttpServlet {
    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Properties genreProperties = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream("genres.properties")) {
            log.info("Loading genres properties...");
            genreProperties.load(resourceStream);
        }

        TreeSet<String> genres = new TreeSet<>();
        for (Object genre : genreProperties.keySet()) {
            genres.add(genre.toString());
        }

        request.setAttribute("genres", genres);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
