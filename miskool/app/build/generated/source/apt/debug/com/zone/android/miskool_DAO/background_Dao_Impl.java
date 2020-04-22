package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.backGroundDB;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class background_Dao_Impl implements background_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfbackGroundDB;

  public background_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfbackGroundDB = new EntityInsertionAdapter<backGroundDB>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `backGroundDB`(`json_id`,`json_name`,`json_value`,`update_status`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, backGroundDB value) {
        stmt.bindLong(1, value.getJsonId());
        if (value.getJsonName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getJsonName());
        }
        if (value.getJsonValue() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getJsonValue());
        }
        if (value.getUpdateStatus() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getUpdateStatus());
        }
      }
    };
  }

  @Override
  public void insertAll(backGroundDB... servics) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfbackGroundDB.insert(servics);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<backGroundDB> getServiceCount() {
    final String _sql = "SELECT * FROM backGroundDB";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfJsonId = _cursor.getColumnIndexOrThrow("json_id");
      final int _cursorIndexOfJsonName = _cursor.getColumnIndexOrThrow("json_name");
      final int _cursorIndexOfJsonValue = _cursor.getColumnIndexOrThrow("json_value");
      final int _cursorIndexOfUpdateStatus = _cursor.getColumnIndexOrThrow("update_status");
      final List<backGroundDB> _result = new ArrayList<backGroundDB>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final backGroundDB _item;
        _item = new backGroundDB();
        final int _tmpJsonId;
        _tmpJsonId = _cursor.getInt(_cursorIndexOfJsonId);
        _item.setJsonId(_tmpJsonId);
        final String _tmpJsonName;
        _tmpJsonName = _cursor.getString(_cursorIndexOfJsonName);
        _item.setJsonName(_tmpJsonName);
        final String _tmpJsonValue;
        _tmpJsonValue = _cursor.getString(_cursorIndexOfJsonValue);
        _item.setJsonValue(_tmpJsonValue);
        final String _tmpUpdateStatus;
        _tmpUpdateStatus = _cursor.getString(_cursorIndexOfUpdateStatus);
        _item.setUpdateStatus(_tmpUpdateStatus);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
