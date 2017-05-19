package com.kenji1947.kenjifit.repo.local;

import com.kenji1947.kenjifit.dao.OrmExercise;
import com.kenji1947.kenjifit.dao.OrmExerciseStep;
import com.kenji1947.kenjifit.dao.OrmI18N;

import java.util.List;

import rx.Observable;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public interface LocalRepository {
    OrmExercise getExerciseById(long id);
    OrmI18N getI18NById(long id);
    List<OrmExerciseStep> getExerciseStepListById(long id);

    List<OrmExercise> getExerciseListByGroup(String group_id);
}
