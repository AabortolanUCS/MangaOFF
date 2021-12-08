package com.ucs.mangaoff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.orm.SugarContext;
import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitClientInstance;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;
import com.ucs.mangaoff.models.Chapter;
import com.ucs.mangaoff.models.SavedChapters;
import com.ucs.mangaoff.models.SavedImages;
import com.ucs.mangaoff.ui.chapters.ChaptersFragment;
import com.ucs.mangaoff.ui.chapters.ChaptersViewModel;
import com.ucs.mangaoff.ui.home.HomeFragment;
import com.ucs.mangaoff.ui.home.HomeViewModel;
import com.ucs.mangaoff.ui.library.LibraryFragment;
import com.ucs.mangaoff.ui.library.LibraryViewModel;
import com.ucs.mangaoff.ui.pages.PagesFragment;
import com.ucs.mangaoff.ui.pages.PagesViewModel;
import com.ucs.mangaoff.ui.search.SearchFragment;
import com.ucs.mangaoff.ui.search.SearchViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private RetrofitEndpoints service;
    private Button search;
    private LinearLayout header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitEndpoints.class);
        setContentView(R.layout.activity_main);
        instanceViews();
        addListeners();
        instanceHomeFragment();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
    }

    private void instanceViews() {
        navigationView = findViewById(R.id.navigationView);
        search = findViewById(R.id.search_button);
        header = findViewById(R.id.header);
    }

    private void addListeners() {
        navigationView.setOnNavigationItemSelectedListener(this);
        search.setOnClickListener(view -> {
            routeToSearch();
        });
    }

    private void instanceHomeFragment() {
        HomeViewModel viewModel = new HomeViewModel(service, this);
        Fragment fragment = HomeFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.container);

        showHeader(!(f instanceof PagesFragment ||f instanceof SearchFragment));

        if(f instanceof HomeFragment ||
           f instanceof PagesFragment ||
           f instanceof LibraryFragment ) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navigation_home) {
            routeToHome();
        } else if (id == R.id.navigation_reading) {
            routeToPages();
        } else if (id == R.id.navigation_library) {
            routeToLibrary();
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void showHeader(Boolean show) {
        header.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    // routes

    public void routeToHome() {
        showHeader(true);
        HomeViewModel viewModel = new HomeViewModel(service, this);
        Fragment fragment = HomeFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    public void routeToPages() {
        showHeader(false);
        PagesViewModel viewModel = new PagesViewModel();
        Fragment fragment = PagesFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    public void routeToLibrary() {
        showHeader(true);
        LibraryViewModel viewModel = new LibraryViewModel(service, this);
        Fragment fragment = LibraryFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    public void routeToChapters(ResponseMangasData mangaData) {
        showHeader(true);
        ChaptersViewModel viewModel = new ChaptersViewModel(service, this, mangaData);
        Fragment fragment = ChaptersFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    public void routeToSearch() {
        showHeader(false);
        SearchViewModel viewModel = new SearchViewModel(service, this);
        Fragment fragment = SearchFragment.newInstance(viewModel);
        openFragment(fragment);
    }
}