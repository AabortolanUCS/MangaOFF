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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.ucs.mangaoff.R;
import com.ucs.mangaoff.models.SavedImages;

import java.util.List;

public class PagesFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";
    private PagesViewModel viewModel;

    private ImageView page;
    private Button previousPage;
    private Button nextPage;
    private ProgressBar progressBar;
    private TextView pageCounter;

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
        progressBar = view.findViewById(R.id.pages_progress);
        pageCounter = view.findViewById(R.id.page_counter);
    }

    private void setListeners() {
        previousPage.setOnClickListener(view -> {
            if(viewModel.currentPage > 0) {
                viewModel.currentPage--;
                viewModel.savePage();
                loadPage();
            }
        });

        nextPage.setOnClickListener(view -> {
            if(viewModel.currentPage < viewModel.pageNumber - 1) {
                viewModel.currentPage++;
                viewModel.savePage();
                loadPage();
            }
        });
    }

    private void loadPage() {
        if(!viewModel.isSaved) {
            String url = "https://uploads.mangadex.org/data/" +
                    viewModel.chapterHash +
                    "/" +
                    viewModel.pages.get(viewModel.currentPage);
            Sprite doubleBounce = new DoubleBounce();
            progressBar.setIndeterminateDrawable(doubleBounce);
            Glide.with(getActivity())
                    .load(url)
                    .into(new SimpleTarget<GlideDrawable>() {
                        @Override
                        public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                            progressBar.setVisibility(View.GONE);
                            page.setImageDrawable(resource);
                        }
                    });
        } else {
            progressBar.setVisibility(View.GONE);
            List<SavedImages> images = SavedImages.find(SavedImages.class ,
                    "chapter_id = ? and page = ?",
                    String.valueOf(viewModel.chapterId),
                    String.valueOf(viewModel.currentPage));
            long totalPages = SavedImages.count(SavedImages.class, "chapter_id = ?", new String[]{String.valueOf(viewModel.chapterId)});
            viewModel.pageNumber = (int) totalPages;
            page.setImageBitmap(images.get(0).getImageBitmap());
        }
        pageCounter.setText((viewModel.currentPage + 1) + " / " + (viewModel.pageNumber + 1));
    }

    public static PagesFragment newInstance(PagesViewModel viewModel) {
        PagesFragment fragment = new PagesFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }
}
