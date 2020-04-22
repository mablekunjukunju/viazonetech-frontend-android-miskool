package com.zone.android.miskool_DAO;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.timetable;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class timetable_Dao_Impl implements timetable_Dao {
  private final RoomDatabase __db;

  public timetable_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<timetable> getAll() {
    final String _sql = "SELECT * FROM timetable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfDateId = _cursor.getColumnIndexOrThrow("date_id");
      final int _cursorIndexOfTime = _cursor.getColumnIndexOrThrow("time");
      final int _cursorIndexOfSub = _cursor.getColumnIndexOrThrow("sub");
      final List<timetable> _result = new ArrayList<timetable>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final timetable _item;
        _item = new timetable();
        final String _tmpDateId;
        _tmpDateId = _cursor.getString(_cursorIndexOfDateId);
        _item.setDateId(_tmpDateId);
        final String _tmpTime;
        _tmpTime = _cursor.getString(_cursorIndexOfTime);
        _item.setTime(_tmpTime);
        final String _tmpSub;
        _tmpSub = _cursor.getString(_cursorIndexOfSub);
        _item.setSub(_tmpSub);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<timetable> getAllMessages() {
    final String _sql = "SELECT * FROM timetable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfDateId = _cursor.getColumnIndexOrThrow("date_id");
      final int _cursorIndexOfTime = _cursor.getColumnIndexOrThrow("time");
      final int _cursorIndexOfSub = _cursor.getColumnIndexOrThrow("sub");
      final List<timetable> _result = new ArrayList<timetable>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final timetable _item;
        _item = new timetable();
        final String _tmpDateId;
        _tmpDateId = _cursor.getString(_cursorIndexOfDateId);
        _item.setDateId(_tmpDateId);
        final String _tmpTime;
        _tmpTime = _cursor.getString(_cursorIndexOfTime);
        _item.setTime(_tmpTime);
        final String _tmpSub;
        _tmpSub = _cursor.getString(_cursorIndexOfSub);
        _item.setSub(_tmpSub);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
