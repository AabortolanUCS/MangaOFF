package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

import java.io.Serializable;

public class SavedChapters extends SugarRecord implements Serializable {

    private String name;
    private String mangaName;
    private byte[] cover;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMangaName() {
        return mangaName;
    }

    public void setMangaName(String mangaName) {
        this.mangaName = mangaName;
    }

    public byte[] getCover() {
        return cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    public Bitmap getCoverBitmap() {
        if(cover != null){
            return Utils.getImage(cover);
        }
        return null;
    }

    public void setCoverBitmap(Bitmap cover) {
        this.cover = Utils.getBytes(cover);
    }
}
