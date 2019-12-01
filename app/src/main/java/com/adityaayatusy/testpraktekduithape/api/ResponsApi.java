package com.adityaayatusy.testpraktekduithape.api;

import com.adityaayatusy.testpraktekduithape.model.DataModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ResponsApi {
    @GET("staffs")
    Call<DataModel> getUsers (@Query("page") int page,
                              @Query("size") int size);
}
