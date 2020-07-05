package com.hosnydevtest.login_mvvm_retrofit.reposetory;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hosnydevtest.login_mvvm_retrofit.remote.Api;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// TODO: 7/5/2020  
public class UserRepository {

    public LiveData<String> userLogin(String email, String password) {

        MutableLiveData<String> liveData = new MutableLiveData<>();

        Api.getInstance().userLoginCall(email, password)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        try {

                            if (response.isSuccessful())
                                liveData.setValue(response.body().string());
                            else
                                liveData.setValue(response.errorBody().string());

                        } catch (IOException ex) {
                            Log.d("UserRepository", "onResponse: " + ex);
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        liveData.setValue(t.getMessage());
                    }
                });

        return liveData;

    }

}
