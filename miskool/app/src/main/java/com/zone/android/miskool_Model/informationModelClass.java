package com.zone.android.miskool_Model;

import android.content.Context;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Presenter.informationPresInterface;
import com.zone.android.miskool_db.AppDatabase;

import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class informationModelClass implements informationmodelInterface {
    @Override
    public void getPersonList(final informationPresInterface informationPresInterface, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Person_det>person_dets=appdb.person_dao().getPersonDetails();
                Log.e("persondetsss","persondetsss "+ person_dets.size());
                informationPresInterface.setList(person_dets);

            }
        }).start();
    }
}
