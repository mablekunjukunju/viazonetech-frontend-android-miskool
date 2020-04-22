package com.zone.android.miskool;

import android.icu.util.Calendar;

import java.sql.Time;

/**
 * Created by Inspiron on 13-10-2018.
 */

public class BaseCalendarEvent {

    long mId;
    int mColor;
    boolean mAllDay;
    String mDuration;
    String mTitle;
    String mDescription;
    String mLocation;

    public BaseCalendarEvent(long id, int color, String title, String description, String location, long dateStart, long dateEnd, int allDay, String duration) {
        this.mId = id;
        this.mColor = color;
        this.mAllDay = (allDay == 1) ? true : false;
        this.mDuration = duration;
        this.mTitle = title;
        this.mDescription = description;
        this.mLocation = location;

    }
}
