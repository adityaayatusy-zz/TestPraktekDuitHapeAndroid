package com.adityaayatusy.testpraktekduithape.api;

import com.adityaayatusy.testpraktekduithape.model.DataModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ResponsApi {
    @GET("staffs")
    Call<DataModel> getUsers (@Query("page") int page,
                              @Query("size") int size);

    @FormUrlEncoded
    @POST("register")
    Call<DataModel> addUsers (@Field("nama") String nama,
                                 @Field("email") String email,
                                 @Field("password") String password,
                                 @Field("jk") String jk);
}
