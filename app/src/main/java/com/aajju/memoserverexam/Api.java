package com.aajju.memoserverexam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by massivcode@gmail.com on 2017. 2. 24. 15:27
 */

public interface Api {




    @POST("member/check")
    @FormUrlEncoded
    Call<Void> duplicateCheck(@Field("email") String email);

    @POST("member/join")
    @FormUrlEncoded
    Call<Void> join(@Field("email") String email, @Field("password") String password);

    @POST("member/login")
    @FormUrlEncoded
    Call<Void> login(@Field("email") String email, @Field("password") String password);

    @GET("memo")
    Call<List<Memo>> getMemoList(@Header("x-auth-token") String token);

    @PUT("memo/")
    @FormUrlEncoded
    Call<Void> updateMemo(@Header("x-auth-token") String token, @Field("id") int id, @Field("title") String title, @Field("contents") String contents);

    @POST("memo/")
    @FormUrlEncoded
    Call<Void> addMemo(@Header("x-auth-token") String token, @Field("title") String title, @Field("contents") String contents);

    @DELETE("memo/")
    @FormUrlEncoded
    Call<Void> deleteMemo(@Header("x-auth-token") String token, @Field("id") int id);

}