package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Config_det;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class config_Dao_Impl implements config_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfConfig_det;

  public config_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfConfig_det = new EntityInsertionAdapter<Config_det>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Config_det`(`service_id`,`service_name`,`service_url`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Config_det value) {
        if (value.getServiceId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getServiceId());
        }
        if (value.getServiceName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getServiceName());
        }
        if (value.getServiceUrl() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getServiceUrl());
        }
      }
    };
  }

  @Override
  public void insertAll(Config_det... services) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfConfig_det.insert(services);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Config_det> getAll() {
    final String _sql = "SELECT * FROM Config_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
      final int _cursorIndexOfServiceName = _cursor.getColumnIndexOrThrow("service_name");
      final int _cursorIndexOfServiceUrl = _cursor.getColumnIndexOrThrow("service_url");
      final List<Config_det> _result = new ArrayList<Config_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Config_det _item;
        _item = new Config_det();
        final String _tmpServiceId;
        _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
        _item.setServiceId(_tmpServiceId);
        final String _tmpServiceName;
        _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
        _item.setServiceName(_tmpServiceName);
        final String _tmpServiceUrl;
        _tmpServiceUrl = _cursor.getString(_cursorIndexOfServiceUrl);
        _item.setServiceUrl(_tmpServiceUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Config_det> getAllMessages() {
    final String _sql = "SELECT * FROM Config_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfServiceId = _cursor.getColumnIndexOrThrow("service_id");
      final int _cursorIndexOfServiceName = _cursor.getColumnIndexOrThrow("service_name");
      final int _cursorIndexOfServiceUrl = _cursor.getColumnIndexOrThrow("service_url");
      final List<Config_det> _result = new ArrayList<Config_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Config_det _item;
        _item = new Config_det();
        final String _tmpServiceId;
        _tmpServiceId = _cursor.getString(_cursorIndexOfServiceId);
        _item.setServiceId(_tmpServiceId);
        final String _tmpServiceName;
        _tmpServiceName = _cursor.getString(_cursorIndexOfServiceName);
        _item.setServiceName(_tmpServiceName);
        final String _tmpServiceUrl;
        _tmpServiceUrl = _cursor.getString(_cursorIndexOfServiceUrl);
        _item.setServiceUrl(_tmpServiceUrl);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
