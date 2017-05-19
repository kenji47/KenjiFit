package com.kenji1947.realmfit;


import com.kenji1947.realmfit.repo.Repository;
import com.kenji1947.realmfit.repo.RepositoryDefault;
import com.kenji1947.realmfit.repo.local.FakeLocalRepository;
import com.kenji1947.realmfit.repo.local.LocalRepository;
import com.kenji1947.realmfit.repo.remote.FakeNetworkStateProvider;
import com.kenji1947.realmfit.repo.remote.FakeRemoteRepository;
import com.kenji1947.realmfit.repo.remote.RemoteRepository;

public class AppSetup {
    public static FakeNetworkStateProvider.NETWORK_STATE network_state =
            FakeNetworkStateProvider.NETWORK_STATE.ONLINE;
}
