package com.kenji1947.realmfit.repo.local;

import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanDesc;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.model.plan.PlanName;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;
import rx.Observable;
import timber.log.Timber;

/**
 * Created by kenji1947 on 17.05.2017.
 */

public class LocalRepositoryDefault implements LocalRepository {

    @Override
    public PlanGoal getPlanGoalByIdAsync(Realm realm, String id) {
        return realm.where(PlanGoal.class).equalTo("_id", id).findFirstAsync();
    }

    @Override
    public RealmResults<PlanGoal> getPlanGoalAllAsync(Realm realm) {
        return realm.where(PlanGoal.class).findAllSortedAsync("pos");
    }



    //TODO Нельзя возвращать из другого потока RO
    @Override
    public Observable<String> createPlanObs(
            String id,
            String name,
            String desc,
            String icon_url,
            boolean isUser,
            int frequency,
            String gender,
            String goal_id) {
        return Observable.fromCallable(() ->
                createPlanFunc(id, name, desc, icon_url, isUser, frequency, gender, goal_id));
    }

    private String createPlanFunc(
            String id, String name, String desc, String icon_url, boolean isUser, int frequency, String gender, String goal_id) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                Plan newPlan = new Plan();
                newPlan.set_id(id);
                newPlan.setIconName("");
                newPlan.setFrequency(3);
                newPlan.setGender("male");
                //todo Можно определять через принадлежность к PlanGoal.id == user
                newPlan.setIsUser(isUser);

                PlanName planName = new PlanName();
                planName.setEn(name);
                PlanDesc planDesc = new PlanDesc();
                planDesc.setEn(desc);

                planName = realm1.copyToRealm(planName);
                planDesc = realm1.copyToRealm(planDesc);
                newPlan.setName(planName);
                newPlan.setDescription(planDesc);

                newPlan = realm1.copyToRealm(newPlan);

//                for (int i = 0; i < 10; i++) {
//                    Timber.d("sleep " + i);
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                        return;
//                    }
//                }
                //Add Plan to Goal
                //TODO findFirst
                PlanGoal planGoal = realm1.where(PlanGoal.class).equalTo("_id", goal_id).findFirst();
                planGoal.getPlans().add(newPlan);
            });
        } finally {
            Timber.d("finally");
            if (realm != null) {
                realm.close();
            }
        }
        return id;
    }

    //todo Dont need a realm

    public void createPlanAsync(
            Realm realm,
            String id,
            String name,
            String desc,
            String icon_url,
            boolean isUser,
            int frequency,
            String gender,
            String goal_id) {

        realm.executeTransactionAsync((realm1) -> {
            Plan newPlan = new Plan();
            newPlan.set_id(id);
            newPlan.setIconName("");
            newPlan.setFrequency(3);
            newPlan.setGender("male");
            newPlan.setIsUser(isUser);

            PlanName planName = new PlanName();
            planName.setEn(name);
            PlanDesc planDesc = new PlanDesc();
            planDesc.setEn(desc);

            planName = realm1.copyToRealm(planName);
            planDesc = realm1.copyToRealm(planDesc);
            newPlan.setName(planName);
            newPlan.setDescription(planDesc);

            newPlan = realm1.copyToRealm(newPlan);

            //Add Plan to Goal
            //TODO findFirst
            PlanGoal planGoal = realm1.where(PlanGoal.class).equalTo("_id", goal_id).findFirst();
            planGoal.getPlans().add(newPlan);
        });
    }

}
