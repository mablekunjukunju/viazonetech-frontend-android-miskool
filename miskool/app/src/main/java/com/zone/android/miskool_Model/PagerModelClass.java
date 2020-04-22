package com.zone.android.miskool_Model;

import android.content.Context;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Presenter.PagerPresInterface;
import com.zone.android.miskool_db.AppDatabase;

import java.util.List;

/**
 * Created by Inspiron on 17-05-2018.
 */

public class PagerModelClass implements PagerModelInterface {
    @Override
    public void getAttList(final PagerPresInterface pagerPresInterface, final Context context, final String stdId) {


        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);



                List<Attributes> attList= appdb.attribute_dao().getAttsStudent(stdId);

                pagerPresInterface.setAttList(attList);

            }
        }).start();
    }
}
