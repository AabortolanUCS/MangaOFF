package com.ucs.mangaoff.ui.search;

import com.ucs.mangaoff.MainActivity;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;
import com.ucs.mangaoff.ui.base.BaseViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends BaseViewModel {
    RetrofitEndpoints service;
    private MainActivity activity;

    public SearchViewModel(RetrofitEndpoints service, MainActivity activity) {
        super();
        this.service = service;
        this.activity = activity;
    }

    public void getMangas(int skip, int take, String title, Callback<ResponseMangas> callback) {
        Call<ResponseMangas> call = service.getMangas("https://api.mangadex.org/manga?limit="+take+"&offset="+skip+"&title="+title+
                "&contentRating[]=safe&includes[]=cover_art&publicationDemographic[]=shounen");
        call.enqueue(new Callback<ResponseMangas>() {
            @Override
            public void onResponse(Call<ResponseMangas> call, Response<ResponseMangas> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseMangas> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

    // routes

    void routeToChapters(ResponseMangasData mangaData) {
        activity.routeToChapters(mangaData);
    }
}
