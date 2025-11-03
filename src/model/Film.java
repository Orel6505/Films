package model;

import jakarta.persistence.*;

@Entity
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private String rating;

    @Column(name = "release_year")
    private short releaseYear;

    @Column(name = "language_id") 
    private short languageId;

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

    public short getLanguageId() { 
        return languageId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setLanguageId(short languageId) { 
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%d) - %s [Lang: %d]", id, title, releaseYear, rating, languageId);
    }
}