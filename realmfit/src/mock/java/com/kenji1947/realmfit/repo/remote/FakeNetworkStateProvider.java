package com.kenji1947.realmfit.repo.remote;

import android.content.Context;

import com.kenji1947.realmfit.AppSetup;

public class FakeNetworkStateProvider implements INetworkStateProvider{
    public enum NETWORK_STATE {
        ONLINE,
        OFFLINE
    }
    private Context context;

    public FakeNetworkStateProvider(Context context) {
        this.context = context;
    }

    public boolean isNetworkAvailable() {

        switch (AppSetup.network_state) {
            case ONLINE:
                return true;
            case OFFLINE:
                return false;
            default:
                return true;
        }
    }
}
