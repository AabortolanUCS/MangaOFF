package com.ucs.mangaoff.ui.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";

    RecyclerView recyclerView;
    HomeListAdapter adapter;
    HomeViewModel viewModel;

    private int skip = 0;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = (HomeViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);
        callService();
    }

    public static HomeFragment newInstance(HomeViewModel viewModel) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void findViewsById(View view) {
       recyclerView = view.findViewById(R.id.home_recycler_view);
    }

    private void callService() {
        String title = "";//"Her Lies";
        viewModel.getMangas(skip, 30, title, new Callback<ResponseMangas>() {
            @Override
            public void onResponse(Call<ResponseMangas> call, Response<ResponseMangas> response) {
                if (response.body() !=  null) {
                    setupList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseMangas> call, Throwable t) {
                Log.d("ERRO", t.getLocalizedMessage());
            }
        });
    }

    private void setupList(List<ResponseMangasData> list) {
        adapter = new HomeListAdapter(list, getActivity(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}