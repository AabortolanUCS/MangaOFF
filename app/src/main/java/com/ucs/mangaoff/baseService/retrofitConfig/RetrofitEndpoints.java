package com.ucs.mangaoff.baseService.retrofitConfig;

import com.ucs.mangaoff.baseService.responseModels.baseResponse.BaseResponse;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitEndpoints {
        @GET
        Call<ResponseMangas> getMangas(@Url String url);

        @GET
        Call<ResponseChapters> getChapters(@Url String url);
}
