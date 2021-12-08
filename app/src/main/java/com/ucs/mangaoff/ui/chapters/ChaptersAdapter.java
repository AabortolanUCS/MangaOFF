package com.ucs.mangaoff.ui.chapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChaptersData;
import com.ucs.mangaoff.models.Chapter;
import com.ucs.mangaoff.models.Manga;
import com.ucs.mangaoff.models.Reading;
import com.ucs.mangaoff.models.SavedChapters;
import com.ucs.mangaoff.models.SavedImages;

import java.util.ArrayList;
import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.ViewHolder> {
    private final List<ResponseChaptersData> localDataSet;
    private final ChaptersViewModel viewModel;

    public ChaptersAdapter(List<ResponseChaptersData> dataSet, ChaptersViewModel viewModel) {
        this.localDataSet = dataSet;
        this.viewModel = viewModel;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final Button downloadButton;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            downloadButton = view.findViewById(R.id.download_button);
        }

        public TextView getName() {
            return name;
        }
    }

    @Override
    public ChaptersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chapter_list_item, viewGroup, false);
        return new ChaptersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChaptersAdapter.ViewHolder viewHolder, final int position) {
        ResponseChaptersData data = localDataSet.get(position);
        String name = "Capítulo " + data.getAttributes().getChapter() + " - " + data.getAttributes().getTranslatedLanguage();
        viewHolder.name.setText(name);
        viewHolder.itemView.setOnClickListener(view -> {
            if(data.getAttributes().getData().size() > 0) {
                saveReading(position);
                viewModel.routeToPages();
            }
        });
        viewHolder.downloadButton.setOnClickListener(view -> saveChapter(position));

    }

    private void saveReading(int position) {
        Reading reading = Reading.last(Reading.class);
        ResponseChaptersData currentChapter = localDataSet.get(position);
        if (reading == null) {
            reading = new Reading();
        }

        Manga manga = new Manga();
        List<Chapter> chapters = new ArrayList<>();
        for (ResponseChaptersData item: localDataSet) {
            Chapter chapter = new Chapter();
            chapter.setLanguage(item.getAttributes().getTranslatedLanguage());
            chapter.setNumber(item.getAttributes().getChapter());
            chapter.setStringPages(item.getAttributes().getData());
            String name = "Capítulo " + item.getAttributes().getChapter() + " - " + item.getAttributes().getTranslatedLanguage();
            chapter.setName(name);
            chapter.setHash(item.getAttributes().getHash());
            chapters.add(chapter);
        }
        manga.setChapters(chapters);
        reading.setCurrentChapter(currentChapter.getAttributes().getChapter());
        reading.setCurrentPage(0);
        reading.setManga(manga);
        reading.setSaved(false);
        reading.setCurrentLanguage(currentChapter.getAttributes().getTranslatedLanguage());
        reading.save();
    }

    private void saveChapter(int position) {
        String currentChapterHash = localDataSet.get(position).getAttributes().getHash();
        List<String> pageList = localDataSet.get(position).getAttributes().getData();
        final int[] savedAux = {0};

        SavedChapters chapter = new SavedChapters();
        String name = "Capitulo " + localDataSet.get(position).getAttributes().getChapter();
        chapter.setName(name);
        chapter.setMangaName(viewModel.mangaData.getAttributes().getTitle().getEn());
        chapter.setCoverBitmap(viewModel.cover);
        long chapterId = chapter.save();

        for (int i = 0; i < pageList.size(); i++){
            String page = pageList.get(i);
            String url = "https://uploads.mangadex.org/data/" +
                    currentChapterHash +
                    "/" +
                    page;

            int finalI = i;
            Glide.with(viewModel.activity)
                    .load(url)
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            SavedImages page = new SavedImages();
                            page.setPage(finalI);
                            page.setChapterId(chapterId);
                            page.setImageBitmap(resource);
                            page.save();
                            savedAux[0]++;
                            if (savedAux[0] == pageList.size()) {
                                Toast toast = Toast.makeText(viewModel.activity, name + " salvo!",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        }
                    });
        }

    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
