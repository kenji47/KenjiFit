package com.kenji1947.kenjifit.repo.db;

import com.kenji1947.kenjifit.dao.OrmExerciseStep;
import com.kenji1947.kenjifit.dao.OrmI18N;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class ObjExerciseStep {
    OrmExerciseStep exerciseStep;
    OrmI18N description;

    public OrmExerciseStep getExerciseStep() {
        return exerciseStep;
    }

    public void setExerciseStep(OrmExerciseStep exerciseStep) {
        this.exerciseStep = exerciseStep;
    }

    public OrmI18N getDescription() {
        return description;
    }

    public void setDescription(OrmI18N description) {
        this.description = description;
    }
}
