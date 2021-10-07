package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Manga implements Serializable{

    public static final String NOME_TABELA = "mangas";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_PHOTO = "photo";
    public static final String COLUNA_DESCRIPTION = "description";
    public static final String COLUNA_AUTHOR_ID = "authorID";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_NAME + " TEXT,"
                    + COLUNA_PHOTO + " BLOB,"
                    + COLUNA_DESCRIPTION + " TEXT,"
                    + COLUNA_AUTHOR_ID + " INTEGER FOREIGN KEY REFERENCES" + Author.NOME_TABELA + "(" + COLUNA_AUTHOR_ID + ")"
                    + ")";

    private long id;
    private String name;
    private byte[] photo;
    private String description;
    private long authorID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }
}
