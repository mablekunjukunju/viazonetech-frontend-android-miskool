package com.zone.android.miskool_Presenter;

import android.content.Context;
import android.util.Log;

import com.zone.android.miskool_Model.loginModelClass;
import com.zone.android.miskool_Model.loginModelInterface;
import com.zone.android.miskool_View.loginViewInterface;

import org.json.JSONObject;

/**
 * Created by Inspiron on 13-11-2017.
 */

public class loginPresClass implements loginPresInterface {

    loginViewInterface loginViewInterface;
    loginModelInterface loginModelInterface;
    String url;

    public loginPresClass(loginViewInterface loginViewInterface){

        this.loginViewInterface = loginViewInterface;

        this.loginModelInterface = new loginModelClass();

    }


    @Override
    public void getToken(String userName, String passWord, String uuid, Context context) {
        loginModelInterface.getToken(userName,passWord,uuid,this, context);

    }

    @Override
    public void getLogin(String userName, String passWord, Context context) {

        loginModelInterface.getLogin(userName,passWord,this, context);
    }




    @Override
    public void getServices(String userName, String passWord, Context context) {

        loginModelInterface.getServices(userName,passWord,this, context);

    }

    @Override
    public void getAccessToken(String userName, String passWord, Context context) {
        loginModelInterface.getAccessToken(userName,passWord,this, context);
    }

    @Override
    public void getTestServices(String userName, String passWord, Context context) {

    }

    @Override
    public void setNavigation() {
        loginViewInterface.setNavigation();
    }

    @Override
    public void createUser(Context context, JSONObject jsonObject) {

    }

    @Override
    public void showError(int errorCode) {
        loginViewInterface.showError(errorCode);
    }

    @Override
    public void showMessage(int code) {

        loginViewInterface.showMessage(code);
    }
}
