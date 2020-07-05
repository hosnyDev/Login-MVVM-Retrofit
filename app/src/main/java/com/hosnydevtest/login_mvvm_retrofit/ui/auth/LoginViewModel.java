package com.hosnydevtest.login_mvvm_retrofit.ui.auth;

import android.util.Patterns;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hosnydevtest.login_mvvm_retrofit.reposetory.UserRepository;

import java.util.Observable;

// TODO: 7/5/2020
public class LoginViewModel extends ViewModel {

    AuthListener authListener;

    public final ObservableField<String> email = new ObservableField<>("");
    public final ObservableField<String> pass = new ObservableField<>("");

    void sendLoginRequest(String email, String pass) {

        if (authListener.isConnection()) {

            if (email.isEmpty()) {
                authListener.onFailure("Please add your email");
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                authListener.onFailure("Please add valid email");
                return;
            }

            if (pass.isEmpty()) {
                authListener.onFailure("Please add your password");
                return;
            }


            if (pass.length() < 6) {
                authListener.onFailure("password must be 6 char");
                return;
            }

            authListener.onStarted();

            LiveData<String> repository =   new UserRepository().userLogin(email, pass);

            authListener.onSuccess(repository);

        } else
            authListener.onFailure("No internet connection");


    }


}