package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Chapter implements Serializable {

    public static final String NOME_TABELA = "chapters";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_PHOTO = "photo";
    public static final String COLUNA_DESCRIPTION = "description";
    public static final String COLUNA_NUMBER = "number";
    public static final String COLUNA_MANGA_ID = "mangaID";

    private long id;
    private String name;
    private byte[] photo;
    private String description;
    private int number;
    private long mangaID;

}
