package com.ucs.mangaoff.ui.home;

import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;
import java.io.Serializable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel implements Serializable {
    RetrofitEndpoints service;

    public HomeViewModel(RetrofitEndpoints service) {
        this.service = service;
    }

    public void getMangas(int skip, int take, String title,Callback<ResponseMangas> callback) {
        Call<ResponseMangas> call = service.getMangas("https://api.mangadex.org/manga?limit="+take+"&offset="+skip+"&title="+title+"&contentRating[]=safe&includes[]=cover_art");
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
}