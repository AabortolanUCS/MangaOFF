package com.ucs.mangaoff.ui.chapters;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChapters;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChaptersData;
import com.ucs.mangaoff.utils.MangaOffUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChaptersFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";
    private ChaptersViewModel viewModel;

    private ImageView cover;
    private TextView title;
    private TextView author;
    private TextView sinopsis;
    private RecyclerView recyclerView;
    private ChaptersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = (ChaptersViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_chapters, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);
        callService();
        populate();
    }

    private void findViewsById(View view) {
        cover = view.findViewById(R.id.cover_image);
        title = view.findViewById(R.id.manga_title);
        author = view.findViewById(R.id.author_name);
        sinopsis = view.findViewById(R.id.sinopsis);
        recyclerView = view.findViewById(R.id.chapters_recycler_view);
    }

    private void callService() {
        String url = "https://uploads.mangadex.org/covers/" +
                viewModel.mangaData.getId() +
                "/" +
                MangaOffUtils.getCoverUrl(viewModel.mangaData.getRelationships());
        Glide.with(getActivity())
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = ThumbnailUtils.extractThumbnail(resource, 1260, 800);
                        bitmap = MangaOffUtils.addGradient(bitmap);
                        cover.setImageBitmap(bitmap);
                    }
                });

        viewModel.getChapters(new Callback<ResponseChapters>() {
            @Override
            public void onResponse(Call<ResponseChapters> call, Response<ResponseChapters> response) {
                if (response.body() != null) {
                    setupList(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ResponseChapters> call, Throwable t) {
                Log.d("TAG", t.getLocalizedMessage());
            }
        });
    }

    private void populate() {
        title.setText(viewModel.mangaData.getAttributes().getTitle().getEn());
        sinopsis.setText(viewModel.mangaData.getAttributes().getDescription().getEn());
    }

    public static ChaptersFragment newInstance(ChaptersViewModel viewModel) {
        ChaptersFragment fragment = new ChaptersFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void setupList(List<ResponseChaptersData> list) {
        adapter = new ChaptersAdapter(list, getActivity(), viewModel);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
