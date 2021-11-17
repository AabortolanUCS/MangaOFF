package com.ucs.mangaoff.ui.home;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
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
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasRelationship;
import com.ucs.mangaoff.utils.MangaOffUtils;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.ViewHolder> {
    private final List<ResponseMangasData> localDataSet;
    private final Activity activity;
    private final HomeViewModel viewModel;

    public HomeListAdapter(List<ResponseMangasData> dataSet, Activity activity, HomeViewModel viewModel) {
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.manga_list_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
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

        viewHolder.itemView.setOnClickListener(view -> viewModel.routeToChapters(localDataSet.get(position)));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
