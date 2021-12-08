package com.ucs.mangaoff.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChapterList implements Serializable {

    private List<Chapter> chapterList;

    public List<Chapter> getChapterList() {
        if (chapterList == null) {
            return new ArrayList<>();
        }
        return chapterList;
    }

    public void setChapterList(List<Chapter> chapterList) {
        this.chapterList = chapterList;
    }
}
