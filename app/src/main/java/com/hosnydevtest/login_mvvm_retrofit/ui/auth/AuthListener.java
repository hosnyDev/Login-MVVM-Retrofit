package com.hosnydevtest.login_mvvm_retrofit.ui.auth;

import androidx.lifecycle.LiveData;

// TODO: 7/5/2020  
public interface AuthListener {

    void onStarted();// to show progressbar

    void onSuccess(LiveData<String> liveData);

    void onFailure(String msg);

    boolean isConnection();

}
