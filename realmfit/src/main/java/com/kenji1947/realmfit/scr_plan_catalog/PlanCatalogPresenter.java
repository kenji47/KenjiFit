package com.kenji1947.realmfit.scr_plan_catalog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.repo.Repository;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kenji1947 on 16.05.2017.
 */

@InjectViewState
public class PlanCatalogPresenter extends MvpPresenter<PlanCatalogView> {
    private Realm realm;
    private Repository repository;


    public PlanCatalogPresenter(Realm realm, Repository repository) {
        this.realm = realm;
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        //Подносить стартовые объекты из презентера, позволит запросить объект только один раз
        //а дальше передавать один и тот же.
        // Для этого нужно хранить в презентере уже собранный. Не надо хранить, он будет в getViewState
        getViewState().initializeView(repository.getLocalRepository().getPlanGoalAllAsync(realm));
    }

    public void onStop() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    //-------------------------------------------


}
