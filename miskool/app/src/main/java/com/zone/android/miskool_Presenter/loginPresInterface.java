package com.zone.android.miskool_Presenter;

import android.content.Context;

import org.json.JSONObject;

/**
 * Created by Inspiron on 13-11-2017.
 */

public interface loginPresInterface {

    //directing calls from view to model

    void getToken(String userName, String passWord,String uuid, Context context);

    void getLogin(String userName, String passWord, Context context);
    void getServices(String userName, String passWord, Context context);

    void getAccessToken(String userName, String passWord, Context context);

    void getTestServices(String userName, String passWord, Context context);

    void setNavigation();
    void createUser(Context context, JSONObject jsonObject);

    void showError(int errorCode);
     void showMessage(int code);


}
