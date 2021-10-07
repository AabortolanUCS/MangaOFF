package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Author implements Serializable {

    public static final String NOME_TABELA = "authors";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_NAME + " TEXT,"
                    + ")";

    private long id;
    private String name;

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
}
