package com.kenji1947.realmfit;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.model.ex_catalog.MuscleGroup;
import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import java.io.IOException;
import java.io.InputStreamReader;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        long start = System.currentTimeMillis();
        //fillDbRealmModel();
        fillDb();
        long end = System.currentTimeMillis();
        Timber.d("Fill table : " + (end - start) + " milliseconds");

    }

    private void fillDb() {
        Gson gson = new GsonBuilder().setLenient().create();
        AssetManager assetManager = getApplication().getResources().getAssets();

        MuscleGroup[] muscleGroups = null;
        try {
            muscleGroups = gson.fromJson(
                    new InputStreamReader(assetManager.open("dictionary.json")), MuscleGroup[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        realm.beginTransaction();
        for (int i = 0; i < muscleGroups.length; i++) {
            MuscleGroup group = muscleGroups[i];
            group = realm.copyToRealm(group);
            for (Exercise ex: group.getExercises()) {
                ex.setMuscleGroup(group);
            }
//            for (Exercise exercise : group.getExercises()) {
//                realm.copyToRealm(exercise); // Persist unmanaged objects
//            }
        }
        realm.commitTransaction();

        //-------------------
        PlanGoal[] plan_goal_array = null;
        try {
            plan_goal_array = gson.fromJson( new InputStreamReader(assetManager.open("plan.json")), PlanGoal[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        realm.beginTransaction();
        for (int i = 0; i < plan_goal_array.length; i++) {
            PlanGoal plan_goal = plan_goal_array[i];
            plan_goal = realm.copyToRealm(plan_goal);
        }
        realm.commitTransaction();

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

        realm.beginTransaction();
        for (int i = 0; i < muscleGroups.length; i++) {
            MuscleGroup group = muscleGroups[i];
            group = realm.copyToRealm(group);
            for (Exercise ex: group.getExercises()) {
                ex.setMuscleGroup(group);
            }
//            for (Exercise exercise : group.getExercises()) {
//                realm.copyToRealm(exercise); // Persist unmanaged objects
//            }
        }
        realm.commitTransaction();

        Plan[] plan_array = null;
        try {
            plan_array = gson.fromJson( new InputStreamReader(assetManager.open("plan.json")), Plan[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        realm.beginTransaction();
        for (int i = 0; i < plan_array.length; i++) {
            Plan plan = plan_array[i];
            plan = realm.copyToRealm(plan);
        }
        realm.commitTransaction();

        RealmResults<MuscleGroup> realmResults = realm.where(MuscleGroup.class).findAll();
        Timber.d("MuscleGroup ExList size: " + realmResults.size());

        long start = System.currentTimeMillis();
        String name = realmResults.get(0).getExercises().get(0).getName().getEn();
        long end = System.currentTimeMillis();
        Timber.d("Read ex name: " + name + " time: " + (end - start) + " milliseconds");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
