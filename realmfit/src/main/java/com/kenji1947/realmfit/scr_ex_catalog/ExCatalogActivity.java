package com.kenji1947.realmfit.scr_ex_catalog;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.ex_catalog.MuscleGroup;

import java.io.IOException;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class ExCatalogActivity extends AppCompatActivity {
    @BindView(R.id.pagerTabs)
    public TabLayout tabLayout;

    @BindView(R.id.dictionaryViewPager)
    public ViewPager viewPager;

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.addEx)
    FloatingActionButton addEx;

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_catalog2);
        ButterKnife.bind(this);

        realm = Realm.getDefaultInstance();
        //fillDbRealmModel();

        FragmentManager fragmentManager = getSupportFragmentManager();

        ExListPagerAdapter pagerAdapter = new ExListPagerAdapter(fragmentManager);
        viewPager.setAdapter(pagerAdapter);
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

        addEx.setOnClickListener((v) -> {
                    Timber.d("Click!");
//            Exercise exercise = new Exercise();
//            exercise.setServerId(UUID.randomUUID().toString());
//            realm.executeTransaction(realm1 -> {
//                realm.where(MuscleGroup.class).equalTo("serverId", "legs").findFirst()
//                        .getExercises().add(exercise);
//                //realm.copyToRealmOrUpdate(exercise);
//            });
                    realm.executeTransaction(realm1 -> {
                        realm.where(MuscleGroup.class).equalTo("serverId", "legs").findFirst()
                                .getExercises().get(0).setWeightForFemale(true);
                    });
                }
        );
    }


    private void fillDbRealmModel() {
        Gson gson = new GsonBuilder().setLenient().create();
        AssetManager assetManager = getApplication().getResources().getAssets();

        MuscleGroup[] muscleGroups = null;
        try {
            muscleGroups = gson.fromJson(
                    new InputStreamReader(assetManager.open("dictionary.json")), MuscleGroup[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        for (int i = 0; i < muscleGroups.length; i++) {
            MuscleGroup group = muscleGroups[i];
            realm.copyToRealm(group);
//            for (Exercise exercise : group.getExercises()) {
//                realm.copyToRealm(exercise); // Persist unmanaged objects
//            }
        }
        realm.commitTransaction();
        realm.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();

    }
}
