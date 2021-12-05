package com.ucs.mangaoff.ui.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangas;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";

    SearchAdapter adapter;
    SearchViewModel viewModel;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EditText search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            viewModel = (SearchViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);
        setListeners();
    }

    public static SearchFragment newInstance(SearchViewModel viewModel) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void findViewsById(View view) {
        recyclerView = view.findViewById(R.id.search_recycler_view);
        progressBar = view.findViewById(R.id.search_progress);
        search = view.findViewById(R.id.et_search);
    }

    private void setListeners() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().isEmpty()) {
                    setupList(new ArrayList<>());
                } else {
                    callService(editable.toString());
                }
            }
        });
    }

    private void callService(String title) {
        Sprite doubleBounce = new DoubleBounce();
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminateDrawable(doubleBounce);
        viewModel.getMangas(0, 30, title, new Callback<ResponseMangas>() {
            @Override
            public void onResponse(Call<ResponseMangas> call, Response<ResponseMangas> response) {
                if (response.body() !=  null) {
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
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
        adapter = new SearchAdapter(list, getActivity(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
