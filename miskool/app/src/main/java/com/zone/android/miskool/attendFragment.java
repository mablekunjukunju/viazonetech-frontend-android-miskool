package com.zone.android.miskool;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zone.android.miskool_Model.attendModelClass;
import com.zone.android.miskool_Model.attendModelInterface;

/**
 * Created by Inspiron on 20-09-2018.
 */

public class attendFragment extends Fragment implements attendFragmentInterface {

    attendModelInterface attendModelInterface = new attendModelClass();
    public attendFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
     //   return super.onCreateView(inflater, container, savedInstanceState);
        String attenddd="";
        if (getArguments() != null) {
            attenddd= this.getArguments().getString("attendance");
        }

        attendModelInterface.getAttendanceDetails("",getActivity(),this);

        return inflater.inflate(R.layout.activity_attend, container, false);
    }

    @Override
    public void setAttendanceDetails() {


       // this is setting attendance details
    }
}
