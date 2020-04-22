package com.zone.android.miskool;

import com.zone.android.miskool_Entitiy.RoutePoint;

import java.util.List;

public interface mapViewInterface {

    void setRouteId(String routeId);
    void setRouteList(List <RoutePoint> routeList);

    void setMessage(int message);

    void updateCurrentLoc(int msg);

}
