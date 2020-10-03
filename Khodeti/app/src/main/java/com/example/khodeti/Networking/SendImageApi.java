package com.example.khodeti.Networking;

import java.lang.annotation.Target;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
public interface SendImageApi {
    @POST("/photo/")
    Call<ReceiveImageResponse> sendImageReq(@Query("Photo") String photo);
}
