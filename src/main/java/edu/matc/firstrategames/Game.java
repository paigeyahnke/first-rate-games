package edu.matc.firstrategames;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * This is the POJO class. The Game Controller class uses the information here so it can map the JSON response
 * into a Java object and return a response format in JSON or HTML.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "id",
        "name",
        "rating",
        "genres",
        "first_release_date"
})
public class Game {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("genres")
    private List<String> genres = new ArrayList<String>();
    @JsonProperty("first_release_date")
    private int releaseYear;

    /**
     * Getter for game id.
     * @return id of the game
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * Setter of the game id
     * @param id game id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Getter of the game name
     * @return game name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * Setter of the game name
     * @param name game name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter of the game rating
     * @return game rating
     */
    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    /**
     * Setter of the game rating
     * @param rating game rating
     */
    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     * Getter of the genre list
     * @return list of genre names
     */
    @JsonProperty("genres")
    public List<String> getGenres() {
        return genres;
    }

    /**
     * Accepts the list of integers representing genres and stores their associated names.
     * @param genres list of genre ids
     */
    @JsonProperty("genres")
    public void setGenres(List<Integer> genres) {
        TreeMap<Integer, String> allGenres = Utilities.getGenres();

        List<String> currentGenres = new ArrayList<>();
        for (Integer genreId : genres) {
            String genre = allGenres.get(genreId);
            if (genre != null) {
                currentGenres.add(genre);
            }
        }

        Collections.sort(currentGenres);
        this.genres = currentGenres;
    }

    /**
     * Getter of the release year
     * @return year of first release
     */
    @JsonProperty("first_release_date")
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * The setter of the year of release.
     * @param releaseYear epoch release date
     * The first_release_date
     */
    @JsonProperty("first_release_date")
    public void setReleaseYear(long releaseYear) {
        LocalDate releaseDate = Instant.ofEpochMilli(releaseYear).atZone(ZoneId.systemDefault()).toLocalDate();
        this.releaseYear = releaseDate.getYear();
    }

    /**
     * Returns the game information, formatted as JSON
     *
     * @return information about the game
     */
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", genres=" + genres +
                ", releaseYear=" + releaseYear +
                '}';
    }


    /**
     * Returns the game information, formatted as HTML
     *
     * @return information about the game
     */
    public String toHTML() {
        String html = "<div class='firstRateGame' id='" + id + "'>"
                + "<h4 id='gameName'>Name: " + name + "</h4>";

        if (rating != null) {
            DecimalFormat df = new DecimalFormat("0");

            html += "<h4 id='gameRating'>Rating: " +
                    df.format(rating) + "/100</h4>";
        }

        html += "<h4 id='gameGenres'>Genres: </h4>"
                + "<ul id='gameGenreList'>";

        for (String genre : genres) {
            html += "<li>" + genre + "</li>";
        }
        html += "</ul>";

        html += "<h4 id='gameReleaseYear'>Year: " + releaseYear + "</h4>";

        html += "</div>";

        return html;

    }

}