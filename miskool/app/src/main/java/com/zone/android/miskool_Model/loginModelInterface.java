package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Presenter.loginPresInterface;

import org.json.JSONObject;

/**
 * Created by Inspiron on 13-11-2017.
 */

public interface loginModelInterface {

    void getToken(String userName, String passWord,String uud, loginPresInterface loginPresInterface, Context context);

    void getLogin(String userName, String passWord, loginPresInterface loginPresInterface, Context context);

    void getAccessToken(String userName, String passWord, loginPresInterface loginPresInterface, Context context);

    void getServices(String userName, String passWord, loginPresInterface loginPresInterface, Context context);



}
