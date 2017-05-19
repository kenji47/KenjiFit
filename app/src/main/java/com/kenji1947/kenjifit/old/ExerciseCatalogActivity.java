package com.kenji1947.kenjifit.old;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.kenji1947.kenjifit.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ExerciseCatalogActivity extends MvpAppCompatActivity{
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);
        ButterKnife.bind(this);

        NavigationView navigationView = (NavigationView) findViewById(R.id.left_drawer);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        if (null == savedInstanceState) {
            initFragment(ExerciseCatalogFragment.newInstance());
        }
    }

    private void initFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, fragment);
        transaction.commit();
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.statistics_navigation_menu_item:
                                //startActivity(new Intent(NotesActivity.this, StatisticsActivity.class));
                                break;
                            case R.id.about_navigation_menu_item:
                                //Toast.makeText(NotesActivity.this, "About", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                        //menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Open the navigation drawer when the home icon is selected from the toolbar.
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
