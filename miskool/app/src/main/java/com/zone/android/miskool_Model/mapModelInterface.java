package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool.mapViewInterface;

public interface mapModelInterface {

    void deleteTable(String studentName, Context context, mapViewInterface mapViewInterface);
    void getRouteList(String studentName, Context context, mapViewInterface mapViewInterface);
    void getStops(String studentName, Context context, mapViewInterface mapViewInterface);

    void getStopsFromDB(String studentName, Context context, mapViewInterface mapViewInterface);

}
