package com.kenji1947.realmfit.scr_plan_catalog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.repo.Repository;
import com.kenji1947.realmfit.util.EspressoIdlingResource;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

/**
 * Created by kenji1947 on 16.05.2017.
 */

@InjectViewState
public class PlanCatalogPresenter extends MvpPresenter<PlanCatalogView> {
    private Realm realm;
    private Repository repository;
    private Subscription subscription;

    public PlanCatalogPresenter(Realm realm, Repository repository) {
        this.realm = realm;
        this.repository = repository;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        loadPlanGoalList();
    }

    public void onStop() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //todo заменить на композит и обновить инфу
        subscription.unsubscribe();
        realm.close();
    }

    //-------------------------------------------
    private void loadPlanGoalList() {
        realm.where(PlanGoal.class).findFirst();


        EspressoIdlingResource.increment();
        subscription = repository.getLocalRepository().getPlanGoalAllAsync(realm)
                .<RealmResults<PlanGoal>>asObservable()
                .filter(RealmResults::isLoaded)
                .filter(RealmResults::isValid)
                .first()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        (planGoals) -> {
                            Timber.d("onNext planGoals size: " + planGoals.size());
                            EspressoIdlingResource.decrement();
                            getViewState().initializeView(planGoals);
                        },
                        (throwable) -> {
                            Timber.d("onError " + throwable);
                            EspressoIdlingResource.decrement();
                        },
                        () -> {

                            Timber.d("onComplete");
                        }
                );
    }

}
