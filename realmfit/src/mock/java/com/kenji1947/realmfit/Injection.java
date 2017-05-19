package com.kenji1947.realmfit;

import android.content.Context;

import com.kenji1947.realmfit.repo.Repository;
import com.kenji1947.realmfit.repo.RepositoryDefault;
import com.kenji1947.realmfit.repo.local.FakeLocalRepository;
import com.kenji1947.realmfit.repo.local.LocalRepository;
import com.kenji1947.realmfit.repo.local.LocalRepositoryDefault;
import com.kenji1947.realmfit.repo.remote.FakeNetworkStateProvider;
import com.kenji1947.realmfit.repo.remote.FakeRemoteRepository;
import com.kenji1947.realmfit.repo.remote.INetworkStateProvider;
import com.kenji1947.realmfit.repo.remote.RemoteRepository;

public class Injection {
    private static Repository sRepository;

    //TODO Change to Fake
    public static INetworkStateProvider provideNetworkState(Context context) {
        return new FakeNetworkStateProvider(context);
    }

    public static Repository provideRepositoryDefault() {
        return sRepository = new RepositoryDefault(
                new LocalRepositoryDefault(), new FakeRemoteRepository());
    }

    public static Repository provideRepository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        return sRepository = new RepositoryDefault(
                localRepository,
                remoteRepository);
    }
}
