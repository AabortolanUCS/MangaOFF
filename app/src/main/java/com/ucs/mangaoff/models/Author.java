package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Author implements Serializable {

    public static final String NOME_TABELA = "authors";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NAME = "name";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_NOME + " TEXT,"
                    + COLUNA_FOTO + " BLOB,"
                    + COLUNA_TELEFONE + " TEXT,"
                    + COLUNA_OBSERVACAO + " TEXT,"
                    + COLUNA_POSICAO + " INTEGER"
                    + ")";

    private long id;
    private String name;

}
