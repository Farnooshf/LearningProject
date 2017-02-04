package com.sematec.learningproject;

import com.orm.SugarRecord;

/**
 * Created by Farnoosh on 3/11/2016.
 */
public class BookModel extends SugarRecord{
    String name;
    String isbn;
    String author;
    String publisher;
    int year;
    String image_url;
    int page_count;

    @Override
    public String toString() {
        return "BookModel{" +
                "name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", image_url='" + image_url + '\'' +
                ", page_count=" + page_count +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getPage_count() {
        return page_count;
    }

    public void setPage_count(int page_count) {
        this.page_count = page_count;
    }

    public BookModel() {
    }

    public BookModel(String name, String isbn, String author, String publisher, int year, String image_url, int page_count) {
        this.name = name;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.image_url = image_url;
        this.page_count = page_count;
    }
}
