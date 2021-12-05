package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.List;

public class Manga implements Serializable {

    private String name;
    private byte[] photo;
    private String hashPhoto;
    private String description;
    private List<Chapter> chapters;
    private Author author;

    public Manga(String name, byte[] photo, String description, List<Chapter> chapters, Author author) {
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.chapters = chapters;
        this.author = author;
    }

    public Manga() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Bitmap getPhotoBitmap() {
        if(photo != null){
            return Utils.getImage(photo);
        }
        return null;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getHashPhoto() {
        return hashPhoto;
    }

    public void setHashPhoto(String hashPhoto) {
        this.hashPhoto = hashPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
