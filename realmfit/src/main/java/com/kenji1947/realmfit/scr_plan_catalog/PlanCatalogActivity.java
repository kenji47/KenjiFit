package com.kenji1947.realmfit.scr_plan_catalog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.kenji1947.realmfit.R;
import com.kenji1947.realmfit.model.plan.Day;
import com.kenji1947.realmfit.model.plan.DayName;
import com.kenji1947.realmfit.model.plan.Plan;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ObjectChangeSet;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by kenji1947 on 13.05.2017.
 */

public class PlanCatalogActivity extends AppCompatActivity {
    @BindView(R.id.updatePlan)
    Button updatePlan;
    @BindView(R.id.updateDay)
    Button updateDay;
    @BindView(R.id.updateDayName)
    Button updateDayName;
    @BindView(R.id.updateWorkout)
    Button updateWorkout;

    Realm realm;
    RealmResults<Plan> planList;
    Plan plan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_catalog2);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        planList = realm.where(Plan.class).findAll();
        plan = planList.get(0);

        dataListenerFunction();

        planList.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<Plan>>() {
            @Override
            public void onChange(RealmResults<Plan> collection, OrderedCollectionChangeSet changeSet) {
                Timber.d("OrderedRealmCollectionChangeListener");
            }
        });
        plan.addChangeListener(new RealmChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel element) {
                Timber.d("RealmChangeListener");
                Timber.d("plan_id: " + ((Plan)(element)).get_id());
            }
        });
        plan.addChangeListener(new RealmObjectChangeListener<RealmModel>() {
            @Override
            public void onChange(RealmModel object, ObjectChangeSet changeSet) {
                Timber.d("RealmObjectChangeListener");
                Timber.d("getChangedFields: " + changeSet.getChangedFields());
                Timber.d("plan_id: " + ((Plan)(object)).get_id());
            }
        });

        updatePlan.setOnClickListener((view) -> {
            realm.executeTransaction(realm1 -> {
                Timber.d("updatePlan");
                Plan plan = realm.where(Plan.class).equalTo("_id", "str1").findFirst();
                plan.setFrequency(new Random().nextInt());
            });
        });

        updateDay.setOnClickListener((view) -> {
            realm.executeTransaction(realm1 -> {
                Timber.d("updateDay");
                Day day = realm.where(Day.class).equalTo("pos", 1).findFirst();
                day.setPos(1);
            });
        });
        updateDayName.setOnClickListener((view) -> {
            realm.executeTransaction(realm1 -> {
                Timber.d("updateDayName");
                DayName day = realm.where(DayName.class).equalTo("ru", "День ног").findFirst();
                day.setRu("День ног");
            });
        });
    }
    private void dataListenerFunction() {
        realm.where(Plan.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .filter(RealmResults::isValid)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    Timber.d("complete size: " + list.size());
                });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //todo убирать лисенеры у всех
        realm.removeAllChangeListeners();
        realm.close();
    }
}
