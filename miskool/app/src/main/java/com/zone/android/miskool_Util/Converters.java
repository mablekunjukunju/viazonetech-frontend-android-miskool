package com.zone.android.miskool_Util;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

/**
 * Created by Inspiron on 08-01-2018.
 */

public  class Converters {
    @TypeConverter
    public Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long dateToTimestamp(Date date) {
        if (date == null) {
            return null;
        } else {
            return date.getTime();
        }
    }
}
