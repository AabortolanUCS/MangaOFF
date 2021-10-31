package com.ucs.mangaoff.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.baseResponse.BaseResponse;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitClientInstance;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
}