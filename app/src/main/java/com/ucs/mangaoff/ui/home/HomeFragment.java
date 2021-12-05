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
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
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
    ProgressBar progressBar;

    private int skip = 0;
    private String title = "";//"Her Lies";

    private boolean occupied = false;

    private List<ResponseMangasData> list;


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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(1)) {
                    remoteCall();
                }
            }
        });
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
       progressBar = view.findViewById(R.id.home_progress);
    }

    private void callService() {

        Sprite doubleBounce = new DoubleBounce();
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setIndeterminateDrawable(doubleBounce);
        remoteCall();
    }

    private void remoteCall() {

        if(!occupied) {

            occupied = true;

            viewModel.getMangas(skip, 30, title, new Callback<ResponseMangas>() {
                @Override
                public void onResponse(Call<ResponseMangas> call, Response<ResponseMangas> response) {
                    if (response.body() !=  null) {
                        progressBar.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        if(list != null && list.size() > 0) {

                            List<ResponseMangasData> newItems = response.body().getData();

                            for (int i = 0; i < newItems.size(); i++) {
                                list.add(skip + i, newItems.get(i));
                            }

                            adapter.notifyItemRangeInserted(skip, newItems.size());

                        } else {
                            list = response.body().getData();
                            setupList();
                        }

                        occupied = false;
                        skip += 30;
                    }
                }

                @Override
                public void onFailure(Call<ResponseMangas> call, Throwable t) {
                    occupied = false;
                    Log.d("ERRO", t.getLocalizedMessage());
                }
            });
        }



    }

    private void setupList() {
        adapter = new HomeListAdapter(list, getActivity(), viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}