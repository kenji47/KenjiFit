package com.kenji1947.kenjifit.screen_ex_catalog;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.repo.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by kenji1947 on 28.04.2017.
 */

public class ExCatalogActivity extends MvpAppCompatActivity {
    @BindView(R.id.pagerTabs)
    public TabLayout tabLayout;
    @BindView(R.id.dictionaryViewPager)
    public ViewPager viewPager;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    private ExListAdapter adapter;
    private CompositeSubscription compositeSubscription;
    private Repository repository;

    private ExListAdapter.ItemClickListener listClickListener = new ExListAdapter.ItemClickListener() {
        @Override
        public void onClick(long ex_id) {
            Timber.d("onClick id: " + ex_id);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_catalog2);
        ButterKnife.bind(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        ExListPagerAdapter adapter = new ExListPagerAdapter(fragmentManager);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Timber.d("onTabSelected pos:" + tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        initToolbar();
        NavigationView navigationView = (NavigationView) findViewById(R.id.left_drawer);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

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
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
