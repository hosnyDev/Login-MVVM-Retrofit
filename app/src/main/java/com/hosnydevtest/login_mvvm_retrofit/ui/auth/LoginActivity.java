package com.hosnydevtest.login_mvvm_retrofit.ui.auth;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;

import com.hosnydevtest.login_mvvm_retrofit.R;
import com.hosnydevtest.login_mvvm_retrofit.databinding.ActivityLoginBinding;
import com.hosnydevtest.login_mvvm_retrofit.util.CheckInternetConn;

import static com.hosnydevtest.login_mvvm_retrofit.util.Hide_Keyboard.hideKeyboard;

// TODO: 7/5/2020  
public class LoginActivity extends AppCompatActivity implements AuthListener {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = new LoginViewModel();

        binding.setLoginview(loginViewModel);
        binding.setPresenter(() -> {

            final String name = binding.loginEmail.getText().toString().trim();
            final String pass = binding.loginPass.getText().toString().trim();

            loginViewModel.sendLoginRequest(name, pass);

        });

        loginViewModel.authListener = this;
    }

    @Override
    public void onStarted() {
        hideKeyboard(this);
        binding.loginProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(LiveData<String> liveData) {
        liveData.observe(this, result -> {
            binding.loginProgressBar.setVisibility(View.GONE);
            showAlert(result);
        });
    }

    @Override
    public void onFailure(String msg) {
        binding.loginProgressBar.setVisibility(View.GONE);
        showAlert(msg);
    }

    @Override
    public boolean isConnection() {
        return new CheckInternetConn(this).isConnection();
    }

    void showAlert(String msg) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(msg)
                .setNegativeButton(getString(R.string.ok_alert_btn), null)
                .create().show();
    }
}