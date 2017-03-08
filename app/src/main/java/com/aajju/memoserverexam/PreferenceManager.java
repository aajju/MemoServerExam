package com.aajju.memoserverexam;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by massivcode@gmail.com on 2017. 3. 8. 14:21
 */

public class PreferenceManager {
    private static final String ITEM_USER_ID = "ITEM_USER_ID";
    private static final String ITEM_USER_PW = "ITEM_USER_PW";
    private static final String ITEM_USER_TOKEN = "ITEM_USER_TOKEN";

    // static 변수에는 s 를 붙인다. 접두사로.
    private static PreferenceManager sInstance = null;
    private SharedPreferences mPreference;

    private PreferenceManager(Context context) {
        // com.aajju.memoserverexam.pref ==> 이런 파일에 값들이 저장된다.
        // 있으면 기존 파일 쓰고, 없으면 새로 만든다..
        mPreference = context.getSharedPreferences(context.getPackageName() + ".pref", Context.MODE_PRIVATE);
    }

    public static synchronized PreferenceManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new PreferenceManager(context);
        }

        return sInstance;
    }

    public String getUserId() {
        return mPreference.getString(ITEM_USER_ID, null);
    }

    public void setUserId(String userId) {
        mPreference.edit().putString(ITEM_USER_ID, userId).apply();
    }

    public String getUserPw() {
        return mPreference.getString(ITEM_USER_PW, null);
    }

    public void setUserPw(String userPw) {
        mPreference.edit().putString(ITEM_USER_ID, userPw).apply();
    }

    public String getUserToken() {
        return mPreference.getString(ITEM_USER_TOKEN, null);
    }

    public void setUserToken(String userToken) {
        mPreference.edit().putString(ITEM_USER_TOKEN, userToken).apply();
    }
}
