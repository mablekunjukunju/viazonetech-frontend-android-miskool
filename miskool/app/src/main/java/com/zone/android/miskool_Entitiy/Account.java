package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 06-03-2018.
 */

@Entity
public class Account {

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "person_id")
    private String personId;

    @ColumnInfo(name = "person_name")
    private String personName;


}
