package com.ucs.mangaoff.ui.library;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;
import com.ucs.mangaoff.ui.home.HomeListAdapter;
import com.ucs.mangaoff.utils.MangaOffUtils;

import java.util.List;

public class LibraryListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private final List<ResponseMangasData> localDataSet;
    private final Activity activity;
    private final LibraryViewModel viewModel;

    public LibraryListAdapter(List<ResponseMangasData> dataSet, Activity activity, LibraryViewModel viewModel) {
        this.localDataSet = dataSet;
        this.activity = activity;
        this.viewModel = viewModel;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private final TextView title;
        private final TextView description;


        public ViewHolder(View view) {
            super(view);
            cover = view.findViewById(R.id.cover);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
        }

        public ImageView getCover() {
            return cover;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }
    }

    @Override
    public HomeListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.manga_list_item, viewGroup, false);

        return new HomeListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeListAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.getTitle().setText(localDataSet.get(position).getAttributes().getTitle().getEn());
        if(localDataSet.get(position).getAttributes().getDescription() != null) {
            viewHolder.getDescription().setText(localDataSet.get(position).getAttributes().getDescription().getEn());
        }
        String url = "https://uploads.mangadex.org/covers/" +
                localDataSet.get(position).getId() +
                "/" +
                MangaOffUtils.getCoverUrl(localDataSet.get(position).getRelationships());
        Glide.with(activity)
                .load(url)
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bitmap = ThumbnailUtils.extractThumbnail(resource, 400, 400);
                        MangaOffUtils.setRoundedThumb(bitmap, bitmap.getWidth(), bitmap.getHeight(), viewHolder.getCover(), activity);
                    }
                });

        viewHolder.itemView.setOnClickListener(view -> viewModel.routeToPages());
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
