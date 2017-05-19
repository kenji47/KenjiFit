package com.kenji1947.realmfit;

import android.content.Context;

import com.kenji1947.realmfit.repo.remote.FakeNetworkStateProvider;
import com.kenji1947.realmfit.repo.remote.INetworkStateProvider;

public class Injection {
    //TODO Change to Fake
    public static INetworkStateProvider provideNetworkState(Context context) {
        return new FakeNetworkStateProvider(context);
    }

}
