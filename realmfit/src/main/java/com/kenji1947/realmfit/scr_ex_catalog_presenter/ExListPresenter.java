package com.kenji1947.realmfit.scr_ex_catalog_presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kenji1947.realmfit.model.ex_catalog.Exercise;
import com.kenji1947.realmfit.model.ex_catalog.MuscleGroup;
import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanGoal;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by kenji1947 on 11.05.2017.
 */

@InjectViewState
public class ExListPresenter extends MvpPresenter<ExListView> {
    private Realm realm;
    private RealmList<Plan> planList;

    private OrderedRealmCollectionChangeListener<RealmList<Plan>> planListListener =
            new OrderedRealmCollectionChangeListener<RealmList<Plan>>() {
                @Override
                public void onChange(RealmList<Plan> collection, OrderedCollectionChangeSet changeSet) {
                    Timber.d("planListListener onChange");
                    getViewState().updatePlansByGoal(changeSet);
                }
            };

    private RealmList<Exercise> exList;
    RealmList<Exercise> realmList;
    public OrderedRealmCollectionChangeListener<RealmList<Exercise>> listener =
            new OrderedRealmCollectionChangeListener<RealmList<Exercise>>() {
                @Override
                public void onChange(RealmList<Exercise> collection, OrderedCollectionChangeSet changeSet) {
                    
                }
            };

    public ExListPresenter(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    //-----------------------------------------------
    public void getPlansByGoal(String goal_id) {
        Timber.d("getPlansByGoal goal_id: " + goal_id);
        planList = realm.where(PlanGoal.class).equalTo("_id", goal_id).findFirst().getPlans();
        getViewState().setPlansByGoal(planList);

    }

    public void loadExListByGroup(String group) {
        if (exList == null) {
            exList = realm
                    .where(MuscleGroup.class)
                    .equalTo("_id", group)
                    .findFirst()
                    .getExercises();
        }
        getViewState().setExList(exList);

    }

    public void loadRx() {
        realm.where(PlanGoal.class)
                .findAllAsync()
                .asObservable()
                .filter(RealmResults::isLoaded)
                .filter(RealmResults::isValid)
                .skip(1)
                .flatMap((list) -> {
                    if (list.size() > 0) {

                    } else {

                    }
                    return Observable.just(list);
                })
                .flatMap(Observable::from)
                .toList()
                .flatMap((list) -> {
                    return Observable.just(list);
                })
                ;
    }
    public void loadRx2() {
        Realm r = Realm.getDefaultInstance();
        Observable.just("")
                .flatMap((s) -> Observable.just(realm.where(PlanGoal.class).findAll()))
                .filter(RealmResults::isLoaded)
                .filter(RealmResults::isValid)
                .flatMap(Observable::from)
                .flatMap((list) -> Observable.just(realm.copyToRealm(new Plan())))
                .toList()
                .doAfterTerminate(() -> {r.close();});
        ;
    }
}
