package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 01-03-2018.
 */

@Entity
public class Alerts {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "alert_id")
    private String alertId;

    @ColumnInfo(name = "alert_date")
    private String alertDate;

    @ColumnInfo(name = "alert_priority")
    private String alertPriority;

    @ColumnInfo(name = "alert_enddate")
    private String alertEndDate;


    @ColumnInfo(name = "alert_sub")
    private String alertSub;

    @ColumnInfo(name = "alert_message")
    private String alertMsg;

    /*@ColumnInfo(name = "alert_add")
    private int alertAdd;*/

    @ColumnInfo(name = "student_id")
    private String studentId;


//active

    @ColumnInfo(name = "active")
    private String active;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
//priority subject

    public String getAlertPriority() {
        return alertPriority;
    }

    public void setAlertPriority(String alertPriority) {
        this.alertPriority = alertPriority;
    }



    public String getAlertEndDate() {
        return alertEndDate;
    }

    public void setAlertEndDate(String alertEndDate) {
        this.alertEndDate = alertEndDate;
    }


   /* public int getAlertAdd() {
        return alertAdd;
    }

    public void setAlertAdd(int alertAdd) {
        this.alertAdd = alertAdd;
    }
*/



    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public String getAlertId() {
        return alertId;
    }

    public void setAlertId(String alertId) {
        this.alertId = alertId;
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }

    public String getAlertSub() {
        return alertSub;
    }

    public void setAlertSub(String alertSub) {
        this.alertSub = alertSub;
    }

    public String getAlertMsg() {
        return alertMsg;
    }

    public void setAlertMsg(String alertMsg) {
        this.alertMsg = alertMsg;
    }



}
