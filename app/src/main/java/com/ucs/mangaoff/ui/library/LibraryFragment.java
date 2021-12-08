package com.ucs.mangaoff.ui.library;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucs.mangaoff.R;
import com.ucs.mangaoff.models.SavedChapters;

import java.util.List;

public class LibraryFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";
    private RecyclerView recyclerView;
    private LibraryListAdapter adapter;
    private LibraryViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = (LibraryViewModel) getArguments().getSerializable(VIEW_MODEL_KEY);
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewsById(view);
        getChapters();
    }

    private void findViewsById(View view) {
        recyclerView = view.findViewById(R.id.library_recycler_view);
    }

    public static LibraryFragment newInstance(LibraryViewModel viewModel) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }

    private void getChapters() {
        List<SavedChapters> savedChapters = SavedChapters.listAll(SavedChapters.class);
        setupList(savedChapters);
    }

    private void setupList(List<SavedChapters> list) {
        adapter = new LibraryListAdapter(list, viewModel.activity, viewModel);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}