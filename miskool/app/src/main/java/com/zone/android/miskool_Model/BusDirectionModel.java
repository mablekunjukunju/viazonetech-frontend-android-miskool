package com.zone.android.miskool_Model;

/**
 * Created by Inspiron on 08-03-2018.
 */

public class BusDirectionModel {
    String curLocName;
    String curLatitude;
    String curLongitude;
    String nextStopIndex;
    String lastStopIndex;

    public String getCurLocName() {
        return curLocName;
    }

    public void setCurLocName(String curLocName) {
        this.curLocName = curLocName;
    }

    public String getCurLatitude() {
        return curLatitude;
    }

    public void setCurLatitude(String curLatitude) {
        this.curLatitude = curLatitude;
    }

    public String getCurLongitude() {
        return curLongitude;
    }

    public void setCurLongitude(String curLongitude) {
        this.curLongitude = curLongitude;
    }

    public String getNextStopIndex() {
        return nextStopIndex;
    }

    public void setNextStopIndex(String nextStopIndex) {
        this.nextStopIndex = nextStopIndex;
    }


}
