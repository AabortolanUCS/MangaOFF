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
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChaptersData;
import com.ucs.mangaoff.models.Chapter;
import com.ucs.mangaoff.models.Manga;
import com.ucs.mangaoff.models.Reading;
import com.ucs.mangaoff.models.SavedChapters;
import com.ucs.mangaoff.ui.home.HomeListAdapter;
import com.ucs.mangaoff.utils.MangaOffUtils;

import java.util.ArrayList;
import java.util.List;

public class LibraryListAdapter extends RecyclerView.Adapter<LibraryListAdapter.ViewHolder> {
    private final List<SavedChapters> localDataSet;
    private final Activity activity;
    private final LibraryViewModel viewModel;

    public LibraryListAdapter(List<SavedChapters> dataSet, Activity activity, LibraryViewModel viewModel) {
        this.localDataSet = dataSet;
        this.activity = activity;
        this.viewModel = viewModel;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView cover;
        private final TextView title;
        private final TextView chapter;


        public ViewHolder(View view) {
            super(view);
            cover = view.findViewById(R.id.saved_cover);
            title = view.findViewById(R.id.saved_title);
            chapter = view.findViewById(R.id.saved_chapter);
        }

        public ImageView getCover() {
            return cover;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getChapter() {
            return chapter;
        }
    }

    @Override
    public LibraryListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.saved_list_item, viewGroup, false);

        return new LibraryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LibraryListAdapter.ViewHolder viewHolder, final int position) {
        SavedChapters chapter = localDataSet.get(position);
        viewHolder.getCover().setImageBitmap(chapter.getCoverBitmap());
        viewHolder.getTitle().setText(chapter.getMangaName());
        viewHolder.getChapter().setText(chapter.getName());
        viewHolder.itemView.setOnClickListener(view -> {
            saveReading(position);
            viewModel.routeToPages();
        });
    }

    private void saveReading(int position) {
        Reading reading = Reading.last(Reading.class);
        SavedChapters currentChapter = localDataSet.get(position);
        if (reading == null) {
            reading = new Reading();
        }
        reading.setSaved(true);
        reading.setChapterId(currentChapter.getId());
        reading.setCurrentPage(0);
        reading.save();
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
