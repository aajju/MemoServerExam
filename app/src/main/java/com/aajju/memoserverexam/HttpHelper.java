package com.aajju.memoserverexam;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by massivcode@gmail.com on 2017. 2. 24. 15:27
 */

public class HttpHelper {
    private static final String RELEASE_BASE_URL = "http://ec2-52-78-131-134.ap-northeast-2.compute.amazonaws.com:9991/api/";

    public static Api getAPI() {
        Retrofit retrofit = createRetrofit();
        return retrofit.create(Api.class);
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RELEASE_BASE_URL)
                .client(getClient())
                .build();
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .build();

    }
}