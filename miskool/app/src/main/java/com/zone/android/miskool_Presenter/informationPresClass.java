package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Model.informationModelClass;
import com.zone.android.miskool_Model.informationmodelInterface;
import com.zone.android.miskool_View.informationViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class informationPresClass implements informationPresInterface {
    informationViewInterface informationViewInterface;
    informationmodelInterface informationModelInterface;


    public informationPresClass(informationViewInterface informationViewInterface){

        this.informationViewInterface=informationViewInterface;
        this.informationModelInterface=new informationModelClass();

    }

    @Override
    public void getPersonList(Context context) {
        informationModelInterface.getPersonList(this,context);
    }

    @Override
    public void setList(List<Person_det> person_dets) {
        informationViewInterface.setList(person_dets);

    }
}
