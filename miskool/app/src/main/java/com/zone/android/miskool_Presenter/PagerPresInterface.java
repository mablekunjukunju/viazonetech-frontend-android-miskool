package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.Person_det;

import java.util.List;

/**
 * Created by Inspiron on 17-05-2018.
 */

public interface PagerPresInterface {
    void getAttList(Context context, String StdId);
    void setAttList(List<Attributes> attList);
}
