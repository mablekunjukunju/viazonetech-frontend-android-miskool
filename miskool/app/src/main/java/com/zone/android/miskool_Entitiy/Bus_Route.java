package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 22-02-2018.
 */

@Entity
public class Bus_Route {

    @NonNull
    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "route_id")
    private String routeId;

    @ColumnInfo(name = "route_name")
    private String routeName;

    @ColumnInfo(name = "bus_id")
    private String busId;

    @ColumnInfo(name = "driver_name")
    private String driverName;

    @ColumnInfo(name = "driver_phone")
    private String driverPhone;

    @ColumnInfo(name = "helper_name")
    private String helperName;

    @ColumnInfo(name = "helper_phone")
    private String helperPhone;

    @ColumnInfo(name = "student_id")
    private String studentId;

    @ColumnInfo(name = "stop_no")
    private int stopNo;

    @ColumnInfo(name = "stop_name")
    private String stopName;

    @ColumnInfo(name = "stop_lat")
    private String stopLat;

    @ColumnInfo(name = "stop_lon")
    private String stopLon;



    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getHelperName() {
        return helperName;
    }

    public void setHelperName(String helperName) {
        this.helperName = helperName;
    }

    public String getHelperPhone() {
        return helperPhone;
    }

    public void setHelperPhone(String helperPhone) {
        this.helperPhone = helperPhone;
    }



    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }


    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getStopNo() {
        return stopNo;
    }

    public void setStopNo(int stopNo) {
        this.stopNo = stopNo;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public String getStopLat() {
        return stopLat;
    }

    public void setStopLat(String stopLat) {
        this.stopLat = stopLat;
    }

    public String getStopLon() {
        return stopLon;
    }

    public void setStopLon(String stopLon) {
        this.stopLon = stopLon;
    }



}
