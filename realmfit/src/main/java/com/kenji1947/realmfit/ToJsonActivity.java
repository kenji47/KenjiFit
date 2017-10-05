package com.kenji1947.realmfit;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import java.io.IOException;
import java.io.InputStreamReader;

import timber.log.Timber;

/**
 * Created by kenji1947 on 28.05.2017.
 */

public class ToJsonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toJson();
    }

    private void toJson() {
        Gson gson = new GsonBuilder().setLenient().create();
        AssetManager assetManager = getApplication().getResources().getAssets();

        //Deserialize
        PlanGoal[] plan_goal_array = null;
        try {
            plan_goal_array = gson.fromJson( new InputStreamReader(assetManager.open("plan.json")), PlanGoal[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Serialize
        Timber.d(gson.toJson(plan_goal_array));



    }
}
