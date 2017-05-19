package com.kenji1947.realmfit.scr_plan_catalog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanDesc;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.model.plan.PlanName;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by kenji1947 on 13.05.2017.
 */

public class PlanCatalogActivity2 extends AppCompatActivity {
    Realm realm;
    private ExpandablePlanAdapter mAdapter;
    @BindView(R.id.changePlan) Button changePlan;
    @BindView(R.id.changePlanName) Button changePlanName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_catalog);

        ButterKnife.bind(this);

        changePlan.setOnClickListener((v) -> {
            Timber.d("onClick changePlan");

            //Plan p1 = new Plan();
//            p1.set_id("mass3");
//            p1.setFrequency(3);
//            p1.setIconName("ex_barbellcurl_icon.jpg");
//
//            PlanName planName = new PlanName();
//            planName.setEn("mass 2");
//            planName.setRu("масса 2");
//
//            PlanDesc planDesc = new PlanDesc();
//            planDesc.setEn("mass2 desc");
//            planDesc.setRu("масса 2 описание");
//
//            p1.setName(planName);
//            p1.setDescription(planDesc);

            realm.executeTransaction((r) -> {
                //todo Запись пройдет через add(). Добавит с одинаковыми ключами
                //Plan recorded = realm.copyToRealmOrUpdate(p1);
                Plan p1 = realm.createObject(Plan.class, "mass4");
                p1.setFrequency(3);
                p1.setIconName("ex_barbellcurl_icon.jpg");

                PlanName planName = new PlanName();
                planName.setEn("mass 2");
                planName.setRu("масса 2");
                planName = realm.copyToRealm(planName);

                PlanDesc planDesc = new PlanDesc();
                planDesc.setEn("mass2 desc");
                planDesc.setRu("масса 2 описание");
                planDesc = realm.copyToRealm(planDesc);

                p1.setName(planName);
                p1.setDescription(planDesc);
                realm.where(PlanGoal.class).equalTo("_id", "mass").findFirst().getPlans().add(p1);
            });
        });
        changePlanName.setOnClickListener((v) -> {
            Timber.d("onClick changePlan");
            Plan p1 = realm.where(Plan.class).equalTo("_id", "mass1").findFirst();
            realm.executeTransaction((r) -> {
                p1.getName().setRu("new ru");
            });
        });

        realm = Realm.getDefaultInstance();

//        PlanGroup g1 = new PlanGroup("Mass", "goal_mass", list);
//        PlanGroup g2 = new PlanGroup("Power", "goal_strength", list);
//        PlanGroup g3 = new PlanGroup("Fat loss", "goal_fatloss", list);
//        final List<PlanGroup> planGroups = Arrays.asList(g1, g2, g3);
//
//        List<PlanGoal> planGoals = new ArrayList<>();
//        planGoals.add(realm.where(PlanGoal.class).equalTo("_id", "mass").findFirst());
//        planGoals.add(realm.where(PlanGoal.class).equalTo("_id", "power").findFirst());
//        planGoals.add(realm.where(PlanGoal.class).equalTo("_id", "fatloss").findFirst());

        //todo move to presenter
        ParentGroup gr1 = new ParentGroup(realm.where(PlanGoal.class).equalTo("_id", "mass").findFirst());
        ParentGroup gr2 = new ParentGroup(realm.where(PlanGoal.class).equalTo("_id", "power").findFirst());
        ParentGroup gr3 = new ParentGroup(realm.where(PlanGoal.class).equalTo("_id", "fatloss").findFirst());

        final List<ParentGroup> groupElement2List = Arrays.asList(gr1, gr2, gr3);
        mAdapter = new ExpandablePlanAdapter(this, groupElement2List);

        //ExpandablePlanAdapter.GroupElement el = mAdapter.new GroupElement(null);

//        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
//            @UiThread
//            @Override
//            public void onParentExpanded(int parentPosition) {
//                PlanGroup expandedRecipe = recipes.get(parentPosition);
//
//                String toastMsg = getResources().getString(R.string.expanded, expandedRecipe.getName());
//                Toast.makeText(PlanCatalogActivity2.this,
//                        toastMsg,
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//
//            @UiThread
//            @Override
//            public void onParentCollapsed(int parentPosition) {
//                PlanGroup collapsedRecipe = recipes.get(parentPosition);
//
//                String toastMsg = getResources().getString(R.string.collapsed, collapsedRecipe.getName());
//                Toast.makeText(PlanCatalogActivity2.this,
//                        toastMsg,
//                        Toast.LENGTH_SHORT)
//                        .show();
//            }
//        });
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.planListRecyclerView);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
