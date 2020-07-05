package com.hosnydevtest.login_mvvm_retrofit.remote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

// TODO: 7/5/2020  
public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> userLoginCall(
            @Field("email") String email,
            @Field("password") String pass);

    static Api getInstance() {
        return new Retrofit.Builder()
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);


    }
}
