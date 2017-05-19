package com.kenji1947.kenjifit.old;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.kenjifit.App;
import com.kenji1947.kenjifit.R;
import com.kenji1947.kenjifit.dao.DaoSession;
import com.kenji1947.kenjifit.dao.OrmExercise;
import com.kenji1947.kenjifit.dao.OrmExerciseStep;
import com.kenji1947.kenjifit.dao.OrmI18N;
import com.kenji1947.kenjifit.parsing.dictionary.Dictionary;
import com.kenji1947.kenjifit.parsing.dictionary.Exercise;
import com.kenji1947.kenjifit.parsing.dictionary.Step;
import com.kenji1947.kenjifit.repo.Repository;
import com.kenji1947.kenjifit.repo.RepositoryDefault;
import com.kenji1947.kenjifit.repo.local.LocalRepoDefault;
import com.kenji1947.kenjifit.screen_ex_catalog.ExListAdapter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ExListActivity extends AppCompatActivity {
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_exercises);

        compositeSubscription = new CompositeSubscription();
        repository = RepositoryDefault.getInstance(
                LocalRepoDefault.getInstance(((App)getApplication()).getDaoSession()), null);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExListAdapter(new ArrayList<>(), listClickListener);
        recyclerView.setAdapter(adapter);
        //createDb();
        loadEx();
    }

    public void loadEx() {
        Timber.d("loadEx");
        compositeSubscription.clear();

        Subscription subscription = repository
                .getExerciseListByGroup("legs")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> {
                            Timber.d("On next size: " + result.size());
                            adapter.addExercises(result);
                        },
                        throwable -> {
                            Timber.d("On error: " + throwable);
                        },
                        () -> {

                        }
                );
        compositeSubscription.add(subscription);
    }

    private void createDb() {
        Gson gson = new GsonBuilder().setLenient().create();
        AssetManager assetManager = getApplication().getResources().getAssets();

        Dictionary[] dictionary_array = null;
        try {
            dictionary_array = gson.fromJson(
                    new InputStreamReader(assetManager.open("dictionary.json")), Dictionary[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DaoSession daoSession = ((App)getApplication()).getDaoSession();
        for (int i = 0; i < dictionary_array.length; i++) {
            Dictionary group = dictionary_array[i];
            for (Exercise exercise : group.getExercises()) {
                //exercise description
                OrmI18N desc_orm = new OrmI18N();
                desc_orm.setEN(exercise.getDescription().getEn());
                desc_orm.setRU(exercise.getDescription().getRu());
                daoSession.getOrmI18NDao().insert(desc_orm);
                //exercise name
                OrmI18N name_orm = new OrmI18N();
                name_orm.setEN(exercise.getName().getEn());
                name_orm.setRU(exercise.getName().getRu());
                daoSession.getOrmI18NDao().insert(name_orm);

                //exercise
                OrmExercise exercise_orm = new OrmExercise();
                exercise_orm.setSERVER_ID(exercise.getServerId());
                exercise_orm.setICON_URL(exercise.getIconUrl());
                exercise_orm.setPERFORMANCE(exercise.getPerformance());
                exercise_orm.setTYPE_NAME(exercise.getTypeName());

                exercise_orm.setWEIGHT(exercise.getWeight() ? 1 : 0);
                exercise_orm.setWEIGHT_FOR_FEMALE(exercise.getWeightForFemale() ? 1 : 0);
                //exercise_orm.setPM_COEFFICIENTS_STR(exercise.getPmCoefficientsStr());
                exercise_orm.setMUSCLE_GROUP_SERVER_ID(group.getServerId());

                exercise_orm.setDESCRIPTION_ID(desc_orm.getId().intValue());
                exercise_orm.setNAME_ID(name_orm.getId().intValue());

                daoSession.getOrmExerciseDao().insert(exercise_orm);

                //exercise steps
                for (Step step : exercise.getSteps()) {
                    OrmExerciseStep exerciseStep = new OrmExerciseStep();

                    //set step description
                    OrmI18N step_desc = new OrmI18N();
                    step_desc.setEN(step.getDescription().getEn());
                    step_desc.setRU(step.getDescription().getRu());
                    daoSession.getOrmI18NDao().insert(step_desc);

                    exerciseStep.setDESCRIPTION_ID(step_desc.getId().intValue());
                    exerciseStep.setICON_URL(step.getIconUrl());
                    exerciseStep.setEXERCISE_ID(exercise_orm.getId().intValue());
                    daoSession.getOrmExerciseStepDao().insert(exerciseStep);
                }
            }
        }

    }
}
