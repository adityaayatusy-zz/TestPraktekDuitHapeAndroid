package com.adityaayatusy.testpraktekduithape.api;

import android.support.annotation.Nullable;

import com.adityaayatusy.testpraktekduithape.model.DataModel;
import com.adityaayatusy.testpraktekduithape.model.SendUserModel;
import com.adityaayatusy.testpraktekduithape.model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ResponsApi {
    @GET("staffs")
    Call<DataModel> getUsers (@Query("page") int page,
                              @Query("size") int size);

    @Headers("Content-Type: application/json")
    @POST("staffs")
    Call<UserModel> addUsers (@Body SendUserModel sendUserModel);
}