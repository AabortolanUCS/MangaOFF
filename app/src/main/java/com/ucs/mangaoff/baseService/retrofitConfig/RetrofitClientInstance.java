package com.ucs.mangaoff.baseService.retrofitConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.DescriptionTypeAdapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://api.mangadex.org";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new DescriptionTypeAdapter())
                    .create();
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}