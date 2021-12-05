package com.ucs.mangaoff.models;

import com.orm.SugarRecord;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class Reading extends SugarRecord implements Serializable {

    private int currentPage;
    private String currentChapter;
    private String currentLanguage;
    private byte[] manga;

    public Reading(int currentPage, String currentChapter, Manga manga, String currentLanguage) {
        this.currentPage = currentPage;
        this.currentChapter = currentChapter;
        this.currentLanguage = currentLanguage;
        setManga(manga);
    }

    public Reading() { }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String getCurrentChapter() {
        return currentChapter;
    }

    public void setCurrentChapter(String currentChapter) {
        this.currentChapter = currentChapter;
    }

    public Manga getManga() {
        return SerializationUtils.deserialize(manga);
    }

    public void setManga(Manga manga) {
        this.manga = SerializationUtils.serialize(manga);;
    }

    public String getCurrentLanguage() {
        return currentLanguage;
    }

    public void setCurrentLanguage(String currentLanguage) {
        this.currentLanguage = currentLanguage;
    }
}
