package com.example.demo_jfx.database.entity;

public class Book {
    private String title;
    private String author;
    private String genre;
    private int published_year;
    private int copies;

    public Book(String title, String author, String genre, int published_year, int copies) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.published_year = published_year;
        this.copies = copies;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPublished_year() {
        return published_year;
    }

    public void setPublished_year(int published_year) {
        this.published_year = published_year;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }
}
