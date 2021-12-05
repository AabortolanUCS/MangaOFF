package com.ucs.mangaoff.ui.library;

import com.ucs.mangaoff.MainActivity;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;
import com.ucs.mangaoff.ui.base.BaseViewModel;

public class LibraryViewModel extends BaseViewModel {

    RetrofitEndpoints service;
    MainActivity activity;

    public LibraryViewModel(RetrofitEndpoints service,
                             MainActivity activity) {
        super();
        this.service = service;
        this.activity = activity;
    }

    void routeToPages() {
        activity.routeToPages();
    }
}