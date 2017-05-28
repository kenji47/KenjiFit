package com.kenji1947.realmfit.repo.local;

import android.support.annotation.UiThread;

import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public interface LocalRepository {

    @UiThread
    PlanGoal getPlanGoalByIdAsync(Realm realm, String id);

    @UiThread
    RealmResults<PlanGoal> getPlanGoalAllAsync(Realm realm);

    //TODO Нельзя возвращать из другого потока RO
    Observable<String> createPlanObs(
            String id,
            String name,
            String desc,
            String icon_url,
            boolean isUser,
            int frequency,
            String gender,
            String goal_id);
}
