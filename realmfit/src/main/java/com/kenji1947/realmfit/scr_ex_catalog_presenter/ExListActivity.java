package com.kenji1947.realmfit.scr_ex_catalog_presenter;

import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.model.plan.Plan;

import io.realm.OrderedCollectionChangeSet;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by kenji1947 on 11.05.2017.
 */

public class ExListActivity extends MvpAppCompatActivity implements ExListView{
    @InjectPresenter
    ExListPresenter presenter;

    @ProvidePresenter
    ExListPresenter providePresenter() {
        return new ExListPresenter(Realm.getDefaultInstance());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setExList(RealmList<Exercise> exList) {

    }

    @Override
    public void setPlansByGoal(RealmList<Plan> planList) {

    }

    @Override
    public void updatePlansByGoal(OrderedCollectionChangeSet changeSet) {

    }
}
