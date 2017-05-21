package com.kenji1947.realmfit.model.plan;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kenji1947 on 12.05.2017.
 */

public class Plan extends RealmObject {
    @PrimaryKey
    private String _id;
    private PlanName name;
    private PlanDesc description;
    private String iconName;
    private int frequency;
    private String gender;
    private RealmList<Day> days;
    private boolean isUser;

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

    public PlanName getName() {
        return name;
    }

    public void setName(PlanName name) {
        this.name = name;
    }

    public PlanDesc getDescription() {
        return description;
    }

    public void setDescription(PlanDesc description) {
        this.description = description;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmList<Day> getDays() {
        return days;
    }

    public void setDays(RealmList<Day> days) {
        this.days = days;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setIsUser(boolean user) {
        isUser = user;
    }
}
