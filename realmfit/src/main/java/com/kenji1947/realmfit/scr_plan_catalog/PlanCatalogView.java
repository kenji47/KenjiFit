package com.kenji1947.realmfit.scr_plan_catalog;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.RealmResults;

/**
 * Created by kenji1947 on 16.05.2017.
 */

public interface PlanCatalogView extends MvpView{

    @StateStrategyType(SingleStateStrategy.class)
    void initializeView(RealmResults<PlanGoal> planGoalList);
}
