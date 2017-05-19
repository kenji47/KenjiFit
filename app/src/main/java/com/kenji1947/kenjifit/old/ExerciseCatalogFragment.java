package com.kenji1947.kenjifit.old;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.screen_ex_catalog.ExListPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by kenji1947 on 28.04.2017.
 */

public class ExerciseCatalogFragment extends MvpAppCompatFragment {
    @BindView(R.id.pagerTabs)
    public TabLayout tabLayout;
    @BindView(R.id.dictionaryViewPager)
    public ViewPager viewPager;

    public static ExerciseCatalogFragment newInstance() {
        return new ExerciseCatalogFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_ex_catalog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FragmentManager fragmentManager = getChildFragmentManager();

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
    }
}
