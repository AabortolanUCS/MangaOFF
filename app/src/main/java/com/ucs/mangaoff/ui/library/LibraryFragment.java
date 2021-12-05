package com.ucs.mangaoff.ui.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ucs.mangaoff.R;

public class LibraryFragment extends Fragment {

    private static final String VIEW_MODEL_KEY = "viewModel";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    public static LibraryFragment newInstance(LibraryViewModel viewModel) {
        LibraryFragment fragment = new LibraryFragment();
        Bundle args = new Bundle();
        args.putSerializable(VIEW_MODEL_KEY, viewModel);
        fragment.setArguments(args);
        return fragment;
    }
}