package com.ucs.mangaoff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ucs.mangaoff.ui.dashboard.ReadingFragment;
import com.ucs.mangaoff.ui.home.HomeFragment;
import com.ucs.mangaoff.ui.notifications.LibraryFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Fragment fragment = HomeFragment.newInstance();
        openFragment(fragment);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if (id == R.id.navigation_home) {
            Fragment fragment = HomeFragment.newInstance();
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
}