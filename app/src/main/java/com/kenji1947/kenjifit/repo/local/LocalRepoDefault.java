package com.kenji1947.kenjifit.repo.local;

import com.kenji1947.kenjifit.dao.DaoSession;
import com.kenji1947.kenjifit.dao.OrmExercise;
import com.kenji1947.kenjifit.dao.OrmExerciseDao;
import com.kenji1947.kenjifit.dao.OrmExerciseStep;
import com.kenji1947.kenjifit.dao.OrmExerciseStepDao;
import com.kenji1947.kenjifit.dao.OrmI18N;
import com.kenji1947.kenjifit.dao.OrmI18NDao;
import com.kenji1947.kenjifit.repo.db.ObjExercise;

import java.util.List;

import rx.Observable;

/**
 * Created by kenji1947 on 27.04.2017.
 */

public class LocalRepoDefault implements LocalRepository{
    private DaoSession daoSession;

    private static LocalRepoDefault INSTANCE;

    private LocalRepoDefault(DaoSession session) {
        this.daoSession = session;
    }
    public static LocalRepository getInstance(DaoSession session) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepoDefault(session);
        }
        return INSTANCE;
    }
    @Override
    public OrmExercise getExerciseById(long id) {
        return daoSession.getOrmExerciseDao().load(id);
    }
    @Override
    public OrmI18N getI18NById(long id) {
        return daoSession.getOrmI18NDao().load(id);
    }
    @Override
    public List<OrmExerciseStep> getExerciseStepListById(long id) {
        return daoSession.getOrmExerciseStepDao()
                .queryBuilder()
                .where(OrmExerciseStepDao.Properties.EXERCISE_ID.eq(id))
                .list();
    }
    @Override
    public List<OrmExercise> getExerciseListByGroup(String group_id) {
        return daoSession.getOrmExerciseDao()
                .queryBuilder()
                .where(OrmExerciseDao.Properties.MUSCLE_GROUP_SERVER_ID.eq(group_id))
                .list();
    }
}
