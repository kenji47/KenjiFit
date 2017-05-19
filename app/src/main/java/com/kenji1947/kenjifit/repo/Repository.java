package com.kenji1947.kenjifit.repo;

import com.kenji1947.kenjifit.repo.db.ObjExercise;

import java.util.List;

import rx.Observable;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public interface Repository {
    Observable<ObjExercise> getExerciseOnly(long id);

    Observable<ObjExercise> getExerciseWithName(long id);

    Observable<ObjExercise> getExerciseWithFull(long id);

    Observable<List<ObjExercise>> getExerciseListByGroup(String group_id);
}
