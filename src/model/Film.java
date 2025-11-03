package model;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @Column(name = "film_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private String rating;

    @Column(name = "release_year")
    private short releaseYear;

    public Film() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public short getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%d) - %s", id, title, releaseYear, rating);
    }
}
