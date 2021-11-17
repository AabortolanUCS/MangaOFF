package com.ucs.mangaoff.ui.pages;

import java.io.Serializable;
import java.util.List;

public class PagesViewModel implements Serializable {

    String chapterHash;
    List<String> pages;
    int currentPage;

    public PagesViewModel(String chapterHash,
                          List<String> pages,
                          int currentPage) {
        this.chapterHash = chapterHash;
        this.pages = pages;
        this.currentPage = currentPage;
    }
}
