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

    private long id;
    private String name;
    private byte[] photo;
    private String description;
    private long authorID;

}
