package com.ucs.mangaoff.ui.pages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ucs.mangaoff.R;

public class PagesFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";
    private PagesViewModel viewModel;

    private ImageView page;
    private Button previousPage;
    private Button nextPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = (PagesViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_pages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);
        setListeners();
        loadPage();
    }

    private void findViewsById(View view) {
        page = view.findViewById(R.id.page);
        previousPage = view.findViewById(R.id.previous_page);
        nextPage = view.findViewById(R.id.next_page);
    }

    private void setListeners() {
        previousPage.setOnClickListener(view -> {
            if(viewModel.currentPage > 0) {
                viewModel.currentPage--;
                loadPage();
            }
        });

        nextPage.setOnClickListener(view -> {
            if(viewModel.currentPage < viewModel.pages.size()) {
                viewModel.currentPage++;
                loadPage();
            }
        });
    }

    private void loadPage() {
        String url = "https://uploads.mangadex.org/data/" +
                viewModel.chapterHash +
                "/" +
                viewModel.pages.get(viewModel.currentPage);
        Glide.with(getActivity())
                .load(url)
                .into(page);
    }

    public static PagesFragment newInstance(PagesViewModel viewModel) {
        PagesFragment fragment = new PagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }
}
