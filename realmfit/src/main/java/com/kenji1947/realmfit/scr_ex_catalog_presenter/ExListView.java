package com.kenji1947.realmfit.scr_ex_catalog_presenter;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.model.plan.Plan;

import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmList;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public interface ExListView extends MvpView {
    @StateStrategyType(SkipStrategy.class)
    void setExList(RealmList<Exercise> exList);
    void setPlansByGoal(RealmList<Plan> planList);
    void updatePlansByGoal(OrderedCollectionChangeSet changeSet);
}
