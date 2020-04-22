package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Inspiron on 30-06-2018.
 */

@Entity
public class backGroundDB {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "json_id")
    private int jsonId;

    public int getJsonId() {
        return jsonId;
    }

    public void setJsonId(int jsonId) {
        this.jsonId = jsonId;
    }

    public String getJsonName() {
        return jsonName;
    }

    public void setJsonName(String jsonName) {
        this.jsonName = jsonName;
    }

    public String getJsonValue() {
        return jsonValue;
    }

    public void setJsonValue(String jsonValue) {
        this.jsonValue = jsonValue;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    @ColumnInfo(name = "json_name")
    private String jsonName;

    @ColumnInfo(name = "json_value")
    private String jsonValue;

    @ColumnInfo(name = "update_status")
    private String updateStatus;


}
