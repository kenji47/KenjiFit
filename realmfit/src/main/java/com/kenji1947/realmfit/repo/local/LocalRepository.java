package com.kenji1947.realmfit.repo.local;

import android.support.annotation.UiThread;

import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public interface LocalRepository {

    @UiThread
    PlanGoal getPlanGoalByIdAsync(Realm realm, String id);

    @UiThread
    RealmResults<PlanGoal> getPlanGoalAllAsync(Realm realm);

    //TODO Передавать сам объект или поля
    void createPlanGoal(String id);

}
