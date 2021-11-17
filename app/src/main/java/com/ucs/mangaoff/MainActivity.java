package com.ucs.mangaoff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ucs.mangaoff.baseService.responseModels.responseMangas.ResponseMangasData;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitClientInstance;
import com.ucs.mangaoff.baseService.retrofitConfig.RetrofitEndpoints;
import com.ucs.mangaoff.ui.chapters.ChaptersFragment;
import com.ucs.mangaoff.ui.chapters.ChaptersViewModel;
import com.ucs.mangaoff.ui.dashboard.ReadingFragment;
import com.ucs.mangaoff.ui.home.HomeFragment;
import com.ucs.mangaoff.ui.home.HomeViewModel;
import com.ucs.mangaoff.ui.notifications.LibraryFragment;
import com.ucs.mangaoff.ui.pages.PagesFragment;
import com.ucs.mangaoff.ui.pages.PagesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private RetrofitEndpoints service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        service = RetrofitClientInstance.getRetrofitInstance().create(RetrofitEndpoints.class);
        setContentView(R.layout.activity_main);
        instanceViews();
        addListeners();
        instanceHomeFragment();
    }

    private void instanceViews() {
        navigationView = findViewById(R.id.navigationView);
    }

    private void addListeners() {
        navigationView.setOnNavigationItemSelectedListener(this);
    }

    private void instanceHomeFragment() {
        HomeViewModel viewModel = new HomeViewModel(service, this);
        Fragment fragment = HomeFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navigation_home) {
            HomeViewModel viewModel = new HomeViewModel(service, this);
            Fragment fragment = HomeFragment.newInstance(viewModel);
            openFragment(fragment);
        } else if (id == R.id.navigation_reading) {
            Fragment fragment = ReadingFragment.newInstance();
            openFragment(fragment);
        } else if (id == R.id.navigation_library) {
            Fragment fragment = LibraryFragment.newInstance();
            openFragment(fragment);
        }
        return true;
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    // routes

    public void routeToChapters(ResponseMangasData mangaData) {
        ChaptersViewModel viewModel = new ChaptersViewModel(service, this, mangaData);
        Fragment fragment = ChaptersFragment.newInstance(viewModel);
        openFragment(fragment);
    }

    public void routeToPages(String chapterHash, List<String> pages, int currentPage) {
        PagesViewModel viewModel = new PagesViewModel(chapterHash, pages, currentPage);
        Fragment fragment = PagesFragment.newInstance(viewModel);
        openFragment(fragment);
    }
}