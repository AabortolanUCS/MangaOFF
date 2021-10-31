package com.ucs.mangaoff.baseService.responseModels.responseChapters;

import com.google.gson.annotations.SerializedName;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.ResponseEnglish;

import java.util.List;

public class ResponseChaptersAttributes {

    @SerializedName("volume")
    private String volume;

    @SerializedName("chapter")
    private String chapter;

    @SerializedName("title")
    private String title;

    @SerializedName("translatedLanguage")
    private String translatedLanguage;

    @SerializedName("hash")
    private String hash;

    @SerializedName("data")
    private List<String> data;

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTranslatedLanguage() {
        return translatedLanguage;
    }

    public void setTranslatedLanguage(String translatedLanguage) {
        this.translatedLanguage = translatedLanguage;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
