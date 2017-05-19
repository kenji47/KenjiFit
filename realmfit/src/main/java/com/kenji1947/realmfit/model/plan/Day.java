package com.kenji1947.realmfit.model.plan;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by kenji1947 on 12.05.2017.
 */

public class Day extends RealmObject {
    private long pos;
    private DayName name;
    private DayDesc desc;
    private RealmList<Workout> workout;

    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }

    public DayName getName() {
        return name;
    }

    public void setName(DayName name) {
        this.name = name;
    }

    public DayDesc getDesc() {
        return desc;
    }

    public void setDesc(DayDesc desc) {
        this.desc = desc;
    }

    public RealmList<Workout> getWorkout() {
        return workout;
    }

    public void setWorkout(RealmList<Workout> workout) {
        this.workout = workout;
    }
}
