package com.kenji1947.realmfit.scr_plan_create;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.kenji1947.realmfit.model.plan.Plan;

import java.util.UUID;

import io.realm.Realm;

/**
 * Created by kenji1947 on 18.05.2017.
 */

public class CreatePlanActivity extends MvpAppCompatActivity {
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realm = Realm.getDefaultInstance();

        Plan plan = realm.createObject(Plan.class, UUID.randomUUID().toString());

        Plan newPlan = new Plan();
        newPlan.set_id(UUID.randomUUID().toString());
        newPlan.setIconName("");
        newPlan.setFrequency(3);
        newPlan.setGender("male");

       // newPlan.setName();
        //newPlan.setDescription();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
