package com.kenji1947.realmfit.model.plan;

import io.realm.RealmObject;

/**
 * Created by kenji1947 on 12.05.2017.
 */

public class Workout extends RealmObject {
    private String exercise_id;
    private int sets;
    private String reps;
    private int res_sec;
    private long pos;

    public String getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(String exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public int getRes_sec() {
        return res_sec;
    }

    public void setRes_sec(int res_sec) {
        this.res_sec = res_sec;
    }

    public long getPos() {
        return pos;
    }

    public void setPos(long pos) {
        this.pos = pos;
    }
}
