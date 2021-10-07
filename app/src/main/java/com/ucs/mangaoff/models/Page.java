package com.ucs.mangaoff.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Page implements Serializable{

    public static final String NOME_TABELA = "pages";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_PHOTO = "photo";
    public static final String COLUNA_NUMBER = "number";
    public static final String COLUNA_CHAPTER_ID = "chapterID";

    private long id;
    private byte[] photo;
    private int number;
    private long chapterID;

}
