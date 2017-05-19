package com.kenji1947.realmfit.scr_ex_catalog;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenji1947 on 28.04.2017.
 */

public class ExListPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles = new ArrayList<>();

    public ExListPagerAdapter(FragmentManager fm) {
        super(fm);
        titles.add("legs");
        titles.add("breast");
        titles.add("back");
        titles.add("shoulders");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return ExListFragment.newInstance(titles.get(position));
    }

    @Override
    public int getCount() {
        return titles.size();
    }
}
