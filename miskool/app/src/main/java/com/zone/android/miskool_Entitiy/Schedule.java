package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 22-10-2018.
 */

@Entity
public class Schedule {


    @ColumnInfo(name = "schedule_id")
    private String scheduleId;


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @ColumnInfo(name = "student_id")
    private String studentId;

    public String getStarttimeNull() {
        return starttimeNull;
    }

    public void setStarttimeNull(String starttimeNull) {
        this.starttimeNull = starttimeNull;
    }

    @ColumnInfo(name = "starttime_null")
    private String starttimeNull;

    @ColumnInfo(name = "endtime_null")
    private String endtimeNull;

    public String getEndtimeNull() {
        return endtimeNull;
    }

    public void setEndtimeNull(String endtimeNull) {
        this.endtimeNull = endtimeNull;
    }
//pkey starttime endtime active subject text

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "pkey")
    private String pkey;

    @ColumnInfo(name = "starttime")
    private String starttime;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPkey() {
        return pkey;
    }

    public void setPkey(String pkey) {
        this.pkey = pkey;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ColumnInfo(name = "endtime")
    private String endtime;

    @ColumnInfo(name = "active")
    private String active;

    @ColumnInfo(name = "subject")
    private String subject;

    @ColumnInfo(name = "text")
    private String text;




}
