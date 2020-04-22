package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Presenter.loginPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Inspiron on 10-11-2017.
 */

public class loginModelClass implements loginModelInterface {


    @Override
    public void getToken(String userName, String passWord, String uud, loginPresInterface loginPresInterface, Context context) {
        Methods.getToken(userName,passWord,uud,context);
    }

    @Override
    public void getLogin(String userName, String passWord, loginPresInterface loginPresInterface, Context context) {

         boolean isUserNameValid = Constants.isUserNameValid(userName);
         boolean isPassWordvalid = Constants.isPassWordValid(passWord);


         if(!isUserNameValid){

             loginPresInterface.showError(Constants.ERROR_USER_NAME_NULL);
         }

         else if(!isPassWordvalid){

             loginPresInterface.showError(Constants.ERROR_PASS_WORD_NULL);

         }
         else if(!isUserNameValid && !isPassWordvalid){
             loginPresInterface.showError(Constants.ERROR_USER_NAME_NULL);
         }
         else{

             loginPresInterface.showMessage(Constants.PASS_VALIDATION);


         }
    }

    @Override
    public void getAccessToken(String userName, String passWord, loginPresInterface loginPresInterface, Context context) {



        Methods.testLogin(userName,passWord,loginPresInterface,context);

    }


    @Override
    public void getServices(String userName, String passWord, loginPresInterface loginPresInterface, Context context) {

        SharedPreferences servicePreference = context.getSharedPreferences(Constants.SERVICE_STATUS_PREFERENCE,Context.MODE_PRIVATE);

        int lastcalledservice = servicePreference.getInt("service",0);

        if(lastcalledservice==0){
            int intStatusCode=  Methods.getToken(userName,passWord,Methods.id(context),context);

            switch(intStatusCode){

                      case 0:
                          loginPresInterface.showError(Constants.ERROR_SERVICE);
                          break;

                      case 1:
                          loginPresInterface.showMessage(Constants.ERROR_CREDENTIALS);
                          break;

                      case 2:

                          SharedPreferences.Editor editor = servicePreference.edit();
                          editor.putInt("service", 1);
                          editor.commit();
                          callRemainingServices(context,loginPresInterface);
                          break;

                  }


          }else{

              callRemainingServices(context,loginPresInterface);

          }

       }

    public void callRemainingServices(Context context,loginPresInterface loginPresInterface){

        SharedPreferences servicePreference = context.getSharedPreferences(Constants.SERVICE_STATUS_PREFERENCE,Context.MODE_PRIVATE);
        int lastcalledservice = servicePreference.getInt("service",0);

        switch(lastcalledservice){

            case 1:
                boolean isPersonPass=Methods.GetPersonWithToken(context);
                if(isPersonPass){

                    SharedPreferences.Editor editor = servicePreference.edit();
                    editor.putInt("service", 2);
                    editor.commit();
                    callRemainingServices(context,loginPresInterface);

                }else{
                    loginPresInterface.showMessage(Constants.ERROR_SERVICE);

                }
                break;

            case 2:
                boolean isMessagePass=Methods.GetMessages(context);
                if(isMessagePass){

                    SharedPreferences.Editor editor = servicePreference.edit();
                    editor.putInt("service", 3);
                    editor.commit();
                    callRemainingServices(context,loginPresInterface);

                }else{
                    loginPresInterface.showMessage(Constants.ERROR_SERVICE);

                }
                break;

            case 3:
                boolean isAlertsPass=Methods.GetAlerts(context);
                if(isAlertsPass){

                    SharedPreferences.Editor editor = servicePreference.edit();
                    editor.putInt("service", 4);
                    editor.commit();
                    callRemainingServices(context,loginPresInterface);

                }else{
                    loginPresInterface.showMessage(Constants.ERROR_SERVICE);

                }
                break;

            case 4:
                boolean isBusPass=Methods.GetBusDetails(context);
                if(isBusPass){

                    SharedPreferences.Editor editor = servicePreference.edit();
                    editor.putInt("service", 5);
                    editor.commit();
                    loginPresInterface.setNavigation();

                }else{
                    loginPresInterface.showMessage(Constants.ERROR_SERVICE);

                }
                break;
        }


    }

    @Override
    public void getTestServices(String userName, String passWord, loginPresInterface loginPresInterface, Context context) {

        boolean isConnectionAvailable= Methods.hasActiveInternetConnection(context);

        if(isConnectionAvailable){

          //  Methods.getConfigRequst(context);
            loginPresInterface.showMessage(Constants.PASS_NETWORK);


        }else{
            loginPresInterface.showError(Constants.ERROR_NETWORK);
        }
    }

    @Override
    public void postLogin(String userName, String passWord, String url) {

    }

    @Override
    public void createUser(Context context, JSONObject jsonObject, loginPresInterface loginPresInterface) {

    }

}
