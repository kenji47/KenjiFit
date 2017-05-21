package com.kenji1947.realmfit.repo.local;

import com.kenji1947.realmfit.model.plan.Plan;
import com.kenji1947.realmfit.model.plan.PlanDesc;
import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.model.plan.PlanName;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

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
        return realm.where(PlanGoal.class).findAllAsync();
    }

    @Override
    public void createPlanGoal(String id) {

    }
    @Override
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

            PlanGoal planGoal = realm1.where(PlanGoal.class).equalTo("_id", goal_id).findFirst();
            planGoal.getPlans().add(newPlan);
            //Add Plan to Goal
        });

    }

}
