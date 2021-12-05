package com.ucs.mangaoff.ui.chapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChaptersData;
import com.ucs.mangaoff.models.Chapter;
import com.ucs.mangaoff.models.Manga;
import com.ucs.mangaoff.models.Reading;

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

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
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
                saveRading(position);
                viewModel.routeToPages();
            }
        });
    }

    private void saveRading(int position) {
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
        reading.setCurrentLanguage(currentChapter.getAttributes().getTranslatedLanguage());
        reading.save();
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
