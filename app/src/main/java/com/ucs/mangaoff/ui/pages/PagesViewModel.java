package com.ucs.mangaoff.ui.pages;

import com.ucs.mangaoff.models.Chapter;
import com.ucs.mangaoff.models.Manga;
import com.ucs.mangaoff.models.Reading;
import com.ucs.mangaoff.ui.base.BaseViewModel;

import java.util.List;

public class PagesViewModel extends BaseViewModel {

    String chapterHash;
    List<String> pages;
    int currentPage;
    boolean isSaved;
    long chapterId;
    int pageNumber;

    public PagesViewModel() {
        super();
        Reading reading = Reading.last(Reading.class);
        if (!reading.isSaved()) {
            Chapter chapter = getCurrentChapter(reading);
            chapterHash = chapter.getHash();
            pages = chapter.getStringPages();
            pageNumber = pages.size();
        } else {
            isSaved = true;
            chapterId = reading.getChapterId();
        }
        currentPage = reading.getCurrentPage();
    }

    private Chapter getCurrentChapter(Reading reading) {
        Manga manga = reading.getManga();
        for (Chapter item: manga.getChapters()) {
          if(reading.getCurrentChapter().equals(item.getNumber()) &&
                  reading.getCurrentLanguage().equals(item.getLanguage())) {
              return item;
          }
        }
        return null;
    }

    void savePage() {
        Reading reading = Reading.last(Reading.class);
        reading.setCurrentPage(currentPage);
        reading.save();
    }
}
