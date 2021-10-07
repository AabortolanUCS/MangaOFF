package com.ucs.mangaoff.models;

import java.io.Serializable;

public class Reading implements Serializable{

    public static final String NOME_TABELA = "readings";

    public static final String COLUNA_ID = "id";
    public static final String COLUNA_CHAPTER_ID = "chapterID";
    public static final String COLUNA_LAST_PAGE = "lastPage";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + NOME_TABELA + "("
                    + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUNA_LAST_PAGE + " INTEGER,"
                    + COLUNA_CHAPTER_ID + " INTEGER FOREIGN KEY REFERENCES" + Chapter.NOME_TABELA + "(" + COLUNA_CHAPTER_ID + ")"
                    + ")";

    private long id;
    private int lastPage;
    private long chapterID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public long getChapterID() {
        return chapterID;
    }

    public void setChapterID(long chapterID) {
        this.chapterID = chapterID;
    }


}
