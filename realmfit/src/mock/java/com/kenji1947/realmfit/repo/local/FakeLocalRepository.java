package com.kenji1947.realmfit.repo.local;

import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public class FakeLocalRepository implements LocalRepository{
    @Override
    public PlanGoal getPlanGoalByIdAsync(Realm realm, String id) {
        return null;
    }

    @Override
    public RealmResults<PlanGoal> getPlanGoalAllAsync(Realm realm) {
        return null;
    }

    @Override
    public Observable<String> createPlanObs(String id, String name, String desc, String icon_url, boolean isUser, int frequency, String gender, String goal_id) {
        return null;
    }


}
