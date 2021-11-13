package com.ucs.mangaoff.ui.chapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChaptersFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";
    private ChaptersViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = (ChaptersViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_chapters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callService();
    }

    private void callService() {
        viewModel.getChapters(new Callback<ResponseChapters>() {
            @Override
            public void onResponse(Call<ResponseChapters> call, Response<ResponseChapters> response) {
                Log.d("TAG", "teste");
            }

            @Override
            public void onFailure(Call<ResponseChapters> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    public static ChaptersFragment newInstance(ChaptersViewModel viewModel) {
        ChaptersFragment fragment = new ChaptersFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }
}
