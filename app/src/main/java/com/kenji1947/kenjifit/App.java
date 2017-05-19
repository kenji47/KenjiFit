package com.kenji1947.kenjifit;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.facebook.stetho.Stetho;
import com.kenji1947.kenjifit.dao.DaoMaster;
import com.kenji1947.kenjifit.dao.DaoSession;

import timber.log.Timber;

/**
 * Created by kenji1947 on 26.04.2017.
 */

public class App extends Application{
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "kenjifit_db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        //clearDb();
    }
    public DaoSession getDaoSession() {
        return daoSession;
    }
    public void clearDb() {
        Timber.d("START Delete");
        daoSession.getOrmExerciseDao().deleteAll();
        daoSession.getOrmI18NDao().deleteAll();
        daoSession.getOrmExerciseStepDao().deleteAll();
        Timber.d("END Delete");
    }
}
