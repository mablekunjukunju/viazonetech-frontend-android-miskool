package com.zone.android.miskool_Util;

/**
 * Created by 3542 on 17-08-2015.
 */
import org.json.JSONException;

public interface ServiceCallback
{

    public void SuccessCallbak(String resp) throws JSONException;

    public void ErrorCallbak(String resp);


}