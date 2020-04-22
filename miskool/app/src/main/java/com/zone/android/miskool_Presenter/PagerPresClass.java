package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Model.PagerModelClass;
import com.zone.android.miskool_Model.PagerModelInterface;
import com.zone.android.miskool_View.pagerViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 17-05-2018.
 */

public class PagerPresClass implements PagerPresInterface {
    pagerViewInterface pagerViewInterface;
    PagerModelInterface pagerModelInterface;

    public PagerPresClass(pagerViewInterface pagerViewInterface ){

        this.pagerViewInterface=pagerViewInterface;
        this.pagerModelInterface=new PagerModelClass();
    }


    @Override
    public void getAttList(Context context,String stdId) {
        pagerModelInterface.getAttList(this,context,stdId);
    }

    @Override
    public void setAttList(List<Attributes> attList) {

        pagerViewInterface.setAttList(attList);
    }
}
