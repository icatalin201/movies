package app.mov.movieteca.models.movies;

import java.util.List;

/**
 * Created by Catalin on 12/7/2017.
 */

public class Movie {

    private Boolean adult;
    private Integer budget;
    private List<Genres> genres;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Double popularity;
    private String backdrop_path;
    private String poster_path;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private String status;
    private String tagline;
    private String title;
    private Double vote_average;
    private Integer vote_count;

    public Movie(Boolean adult, Integer budget, List<Genres> genres, Integer id, String imdb_id,
                 String original_language, String original_title, String overview, Double popularity,
                 String backdrop_path, String poster_path, String release_date, Integer revenue,
                 Integer runtime, String status, String tagline, String title, Double vote_average, Integer vote_count) {
        this.adult = adult;
        this.budget = budget;
        this.genres = genres;
        this.id = id;
        this.imdb_id = imdb_id;
        this.original_language = original_language;
        this.original_title = original_title;
        this.overview = overview;
        this.popularity = popularity;
        this.backdrop_path = backdrop_path;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }
}
