package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Page implements Serializable{

    public static final String NOME_TABELA = "pages";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_PHOTO = "photo";
    public static final String COLUNA_NUMBER = "number";
    public static final String COLUNA_CHAPTER_ID = "chapterID";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_PHOTO + " BLOB,"
                    + COLUNA_NUMBER + " INTEGER,"
                    + COLUNA_CHAPTER_ID + " INTEGER FOREIGN KEY REFERENCES" + Chapter.NOME_TABELA + "(" + COLUNA_CHAPTER_ID + ")"
                    + ")";
    private long id;
    private byte[] photo;
    private int number;
    private long chapterID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getChapterID() {
        return chapterID;
    }

    public void setChapterID(long chapterID) {
        this.chapterID = chapterID;
    }
}
