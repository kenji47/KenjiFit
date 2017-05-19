package com.kenji1947.realmfit.repo;

import android.support.annotation.UiThread;

import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.repo.local.LocalRepository;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kenji1947 on 15.05.2017.
 */

public interface Repository {

    @UiThread
    PlanGoal getPlanGoalById(Realm realm, String id);

    @UiThread
    RealmResults<PlanGoal> getPlanGoalAll(Realm realm);

    LocalRepository getLocalRepository();
}
