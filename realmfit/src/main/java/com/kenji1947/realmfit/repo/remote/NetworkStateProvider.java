package com.kenji1947.realmfit.repo.remote;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by kenji1947 on 05.03.2017.
 */

public class NetworkStateProvider implements INetworkStateProvider {
    private Context context;

    public NetworkStateProvider(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
