package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Favorite implements Serializable{

    public static final String NOME_TABELA = "favorites";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_MANGA_ID = "mangaID";

    private long id;
    private long mangaID;

}
