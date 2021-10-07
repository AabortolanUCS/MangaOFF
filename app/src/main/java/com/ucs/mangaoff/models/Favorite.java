package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Favorite implements Serializable{

    public static final String NOME_TABELA = "favorites";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_MANGA_ID = "mangaID";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_MANGA_ID + " INTEGER FOREIGN KEY REFERENCES" + Manga.NOME_TABELA + "(" + COLUNA_MANGA_ID + ")"
                    + ")";

    private long id;
    private long mangaID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMangaID() {
        return mangaID;
    }

    public void setMangaID(long mangaID) {
        this.mangaID = mangaID;
    }
}
