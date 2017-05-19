package com.kenji1947.kenjifit.repo.db;

import com.kenji1947.kenjifit.dao.OrmExercise;
import com.kenji1947.kenjifit.dao.OrmI18N;

import java.util.List;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ObjExercise {
    OrmExercise exercise;
    OrmI18N name;
    OrmI18N description;
    List<ObjExerciseStep> stepList;

    public OrmExercise getExercise() {
        return exercise;
    }

    public void setExercise(OrmExercise exercise) {
        this.exercise = exercise;
    }

    public OrmI18N getName() {
        return name;
    }

    public void setName(OrmI18N name) {
        this.name = name;
    }

    public OrmI18N getDescription() {
        return description;
    }

    public void setDescription(OrmI18N description) {
        this.description = description;
    }

    public List<ObjExerciseStep> getStepList() {
        return stepList;
    }

    public void setStepList(List<ObjExerciseStep> stepList) {
        this.stepList = stepList;
    }
}
