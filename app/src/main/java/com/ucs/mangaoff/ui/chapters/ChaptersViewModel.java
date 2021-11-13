package com.ucs.mangaoff.ui.chapters;

import com.ucs.mangaoff.MainActivity;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChaptersViewModel implements Serializable {

    RetrofitEndpoints service;
    MainActivity activity;
    String mangaHash;

    public ChaptersViewModel(RetrofitEndpoints service, MainActivity activity, String mangaHash) {
        this.service = service;
        this.activity = activity;
        this.mangaHash = mangaHash;
    }

    public void getChapters(Callback<ResponseChapters> callback) {
        Call<ResponseChapters> call = service.getChapters("(https://api.mangadex.org/manga/" +
                mangaHash +
                "/feed?limit=96&order[volume]=desc&order[chapter]=desc&offset=960");
        call.enqueue(new Callback<ResponseChapters>() {
            @Override
            public void onResponse(Call<ResponseChapters> call, Response<ResponseChapters> response) {
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call<ResponseChapters> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}
