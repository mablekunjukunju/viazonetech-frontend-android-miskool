package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Presenter.PagerPresInterface;
import com.zone.android.miskool_Presenter.mainPresInterface;

/**
 * Created by Inspiron on 17-05-2018.
 */

public interface PagerModelInterface {
    void getAttList(PagerPresInterface pagerPresInterface, Context context,String StdId);

}
