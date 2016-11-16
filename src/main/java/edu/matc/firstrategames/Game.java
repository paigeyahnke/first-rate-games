package edu.matc.firstrategames;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
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

    // TODO rework to return real dates (just the year) and possibly genre

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("rating")
    private Double rating;
    @JsonProperty("genres")
    private List<Integer> genres = new ArrayList<Integer>();
    @JsonProperty("first_release_date")
    private int releaseYear;

    /**
     *
     * @return
     * The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The rating
     */
    @JsonProperty("rating")
    public Double getRating() {
        return rating;
    }

    /**
     *
     * @param rating
     * The rating
     */
    @JsonProperty("rating")
    public void setRating(Double rating) {
        this.rating = rating;
    }

    /**
     *
     * @return
     * The genres
     */
    @JsonProperty("genres")
    public List<Integer> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     * The genres
     */
    @JsonProperty("genres")
    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     * The releaseYear
     */
    @JsonProperty("first_release_date")
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     *
     * @param releaseYear
     * The first_release_date
     */
    @JsonProperty("first_release_date")
    public void setReleaseYear(long releaseYear) {
        LocalDate releaseDate = Instant.ofEpochMilli(releaseYear).atZone(ZoneId.systemDefault()).toLocalDate();
        this.releaseYear = releaseDate.getYear();
    }


    /**
     * This is what the game response will return if the data reponse is JSON
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
     * This is what the game response will return if the data reponse is HTML
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

        for (int genre : genres) {
            html += "<li>" + genre + "</li>";
        }
        html += "</ul>";

        html += "<h4 id='gameReleaseYear'>Year: " + releaseYear + "</h4>";

        html += "</div>";

        return html;

    }

}