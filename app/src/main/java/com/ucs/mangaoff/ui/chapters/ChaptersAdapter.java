package com.ucs.mangaoff.ui.chapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.baseService.responseModels.responseChapters.ResponseChaptersData;

import java.util.List;

public class ChaptersAdapter extends RecyclerView.Adapter<ChaptersAdapter.ViewHolder> {
    private final List<ResponseChaptersData> localDataSet;
    private final Activity activity;
    private final ChaptersViewModel viewModel;

    public ChaptersAdapter(List<ResponseChaptersData> dataSet, Activity activity, ChaptersViewModel viewModel) {
        this.localDataSet = dataSet;
        this.activity = activity;
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getAttributes().getData().size() > 0) {
                    viewModel.routeToPages(data.getAttributes().getHash() ,data.getAttributes().getData());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
