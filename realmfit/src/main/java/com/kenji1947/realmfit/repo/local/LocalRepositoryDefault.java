package com.kenji1947.realmfit.repo.local;

import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public class LocalRepositoryDefault implements LocalRepository {

    @Override
    public PlanGoal getPlanGoalByIdAsync(Realm realm, String id) {
        return realm.where(PlanGoal.class).equalTo("_id", id).findFirstAsync();
    }

    @Override
    public RealmResults<PlanGoal> getPlanGoalAllAsync(Realm realm) {
        return realm.where(PlanGoal.class).findAllAsync();
    }

    @Override
    public void createPlanGoal(String id) {

    }

}
