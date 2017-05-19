package com.kenji1947.kenjifit.repo;

import com.kenji1947.kenjifit.dao.OrmExercise;
import com.kenji1947.kenjifit.dao.OrmExerciseStep;
import com.kenji1947.kenjifit.repo.db.ObjExercise;
import com.kenji1947.kenjifit.repo.db.ObjExerciseStep;
import com.kenji1947.kenjifit.repo.local.LocalRepository;
import com.kenji1947.kenjifit.repo.remote.RemoteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import timber.log.Timber;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class RepositoryDefault implements Repository {
    private static RepositoryDefault INSTANCE;
    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    //TODO Отказ Синглтона
    public RepositoryDefault(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public static Repository getInstance(LocalRepository localDataSource,
                                         RemoteRepository remoteDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new RepositoryDefault(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Observable<ObjExercise> getExerciseOnly(long id) {
        ObjExercise objExercise = new ObjExercise();
        objExercise.setExercise(localRepository.getExerciseById(id));
        return Observable.just(objExercise);
    }
    @Override
    public Observable<ObjExercise> getExerciseWithName(long id) {
        ObjExercise objExercise = new ObjExercise();
        objExercise.setExercise(localRepository.getExerciseById(id));
        objExercise.setName(localRepository.getI18NById(objExercise.getExercise().getNAME_ID()));
        return Observable.just(objExercise);
    }
    @Override
    public Observable<ObjExercise> getExerciseWithFull(long id) {
        ObjExercise objExercise = new ObjExercise();
        objExercise.setExercise(localRepository.getExerciseById(id));
        objExercise.setName(localRepository.getI18NById(objExercise.getExercise().getNAME_ID()));
        objExercise.setDescription(localRepository.getI18NById(objExercise.getExercise().getDESCRIPTION_ID()));

        List<OrmExerciseStep> ormExerciseStepList = localRepository.getExerciseStepListById(id);
        List<ObjExerciseStep> objExerciseSteps = new ArrayList<>();
        for (OrmExerciseStep step : ormExerciseStepList) {
            ObjExerciseStep objExerciseStep = new ObjExerciseStep();
            objExerciseStep.setExerciseStep(step);
            objExerciseStep.setDescription(localRepository.getI18NById(step.getDESCRIPTION_ID()));
            objExerciseSteps.add(objExerciseStep);
        }
        objExercise.setStepList(objExerciseSteps);
        return Observable.just(objExercise);
    }
    @Override
    public Observable<List<ObjExercise>> getExerciseListByGroup(String group_id) {
        long startTime = System.nanoTime();

        List<OrmExercise> ormExercises = localRepository.getExerciseListByGroup(group_id);
        List<ObjExercise> objExercises = new ArrayList<>();
        for (OrmExercise o : ormExercises) {
            objExercises.add(parseExerciseWithName(o));
        }
        long elapsedTime = System.nanoTime() - startTime;
        Timber.d("TIME: getExerciseListByGroup: " + + elapsedTime/1000000 + " ms");
        return Observable.just(objExercises);
    }

    private ObjExercise parseExerciseWithName(OrmExercise ormExercise) {
        ObjExercise objExercise = new ObjExercise();
        objExercise.setExercise(ormExercise);
        objExercise.setName(localRepository.getI18NById(objExercise.getExercise().getNAME_ID()));
        return objExercise;
    }
}
