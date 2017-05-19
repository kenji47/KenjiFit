package com.kenji1947.realmfit.repo;

import com.kenji1947.realmfit.model.plan.PlanGoal;
import com.kenji1947.realmfit.repo.local.LocalRepository;
import com.kenji1947.realmfit.repo.remote.RemoteRepository;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kenji1947 on 15.05.2017.
 */

public class RepositoryDefault implements Repository{
    LocalRepository localRepository;
    RemoteRepository remoteRepository;

    public RepositoryDefault(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public LocalRepository getLocalRepository() {
        return localRepository;
    }


    @Override
    public PlanGoal getPlanGoalById(Realm realm, String id) {
        return null;
    }

    @Override
    public RealmResults<PlanGoal> getPlanGoalAll(Realm realm) {
        return null;
    }
}
