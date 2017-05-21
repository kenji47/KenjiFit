package com.kenji1947.realmfit.scr_plan_catalog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.kenji1947.realmfit.Injection;
import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.scr_plan_create.CreatePlanActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public class PlanCatalogActivity3 extends MvpAppCompatActivity implements PlanCatalogView {
    @InjectPresenter
    PlanCatalogPresenter presenter;
    @ProvidePresenter
    PlanCatalogPresenter providePresenter() {
        return new PlanCatalogPresenter(Realm.getDefaultInstance(), Injection.provideRepositoryDefault());
    }

    @BindView(R.id.createPlan) Button createPlan;
    private ExpandablePlanAdapter adapter;
    @BindView(R.id.planListRecyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("onCreate");
        setContentView(R.layout.activity_plan_catalog3);
        ButterKnife.bind(this);

        Timber.d("onCreate End");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.d("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.d("onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isChangingConfigurations()) {
            presenter.onStop();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        Timber.d("onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        //TODO Разобрать
        adapter.onRestoreInstanceState(savedInstanceState);
    }
    //-------------------------------------

    //Инициализация - это часть кода из onCreate. Будет Вызван сразу после onCreate
    @Override
    public void initializeView(RealmResults<PlanGoal> planGoalList) {
        Timber.d("initializeView");

        long start = System.currentTimeMillis();
//        ParentGroup gr1 = new ParentGroup(planGoalList.get(0));
//        ParentGroup gr2 = new ParentGroup(planGoalList.get(1));
//        ParentGroup gr3 = new ParentGroup(planGoalList.get(2));

//        ParentGroup gr1 = new ParentGroup(planGoalList.where().equalTo("_id", "mass").findFirst());
//        ParentGroup gr2 = new ParentGroup(planGoalList.where().equalTo("_id", "power").findFirst());
//        ParentGroup gr3 = new ParentGroup(planGoalList.where().equalTo("_id", "fatloss").findFirst());

        ParentGroup gr1 = new ParentGroup(planGoalList.where().equalTo("_id", "mass").findFirst());
        ParentGroup gr2 = new ParentGroup(planGoalList.where().equalTo("_id", "power").findFirst());
        ParentGroup gr3 = new ParentGroup(planGoalList.where().equalTo("_id", "fatloss").findFirst());
        final List<ParentGroup> groupElement2List = Arrays.asList(gr1, gr2, gr3);

        long end = System.currentTimeMillis();
        Timber.d("Fill ParentGroup : " + (end - start) + " milliseconds");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ExpandablePlanAdapter(this, groupElement2List);
        recyclerView.setAdapter(adapter);

        createPlan.setOnClickListener((v) -> {
            Intent intent = new Intent(PlanCatalogActivity3.this, CreatePlanActivity.class);
            startActivity(intent);
        });

        Timber.d("initializeView End");


        //TODO Почему задержка при перевовороте
        //TODO Как кешируется результат realm
            //First query will execute in 6 ms, second query in 0 ms

        //Почему задержка на методах viewState
            //Эти методы вызываются сразу после onCreate
    }
}
