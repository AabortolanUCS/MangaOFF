package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Reading implements Serializable{

    public static final String NOME_TABELA = "readings";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_CHAPTER_ID = "chapterID";
    public static final String COLUNA_LAST_PAGE = "lastPage";

    private long id;
    private long chapterID;
    private int lastPage;
}
