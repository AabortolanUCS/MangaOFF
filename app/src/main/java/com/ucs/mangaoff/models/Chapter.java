package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Chapter implements Serializable {

    private String name;
    private byte[] photo;
    private String description;
    private String number;
    private List<Page> pages;
    private String language;
    private String hash;

    public Chapter(String name, byte[] photo, String description, String number, List<Page> pages, String language, String hash) {
        this.name = name;
        this.photo = photo;
        this.description = description;
        this.number = number;
        this.pages = pages;
        this.language = language;
        this.hash = hash;
    }

    public Chapter() { }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setStringPages(List<String> hashs) {
        List<Page> pages = new ArrayList<>();
        for (String hash: hashs) {
            Page page = new Page();
            page.setHashPage(hash);
            pages.add(page);
        }
        this.pages = pages;
    }

    public List<String> getStringPages() {
        List<String> hashs = new ArrayList<>();
        for (Page item: pages) {
            hashs.add(item.getHashPage());
        }
       return hashs;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
