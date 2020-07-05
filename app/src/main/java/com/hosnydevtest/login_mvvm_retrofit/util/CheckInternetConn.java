package com.hosnydevtest.login_mvvm_retrofit.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// TODO: 7/5/2020  
public class CheckInternetConn {

    private final Context context;

    public CheckInternetConn(Context context) {

        this.context = context;
    }

    public boolean isConnection() {

        ConnectivityManager conn = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conn != null) {

            NetworkInfo info = conn.getActiveNetworkInfo();
            return info != null && info.isConnected();

        }

        return false;
    }
}
