package com.kenji1947.realmfit.model.plan;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kenji1947 on 14.05.2017.
 */

public class PlanGoal extends RealmObject {
    @PrimaryKey
    private String _id;
    private PlanGoalName name;
    private String iconName;
    private RealmList<Plan> plans;

    public String getIconName() {
        return iconName;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public PlanGoalName getName() {
        return name;
    }

    public void setName(PlanGoalName name) {
        this.name = name;
    }

    public RealmList<Plan> getPlans() {
        return plans;
    }

    public void setPlans(RealmList<Plan> plans) {
        this.plans = plans;
    }
}
