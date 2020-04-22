package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Alerts;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class alert_Dao_Impl implements alert_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAlerts;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserAlerts;

  public alert_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAlerts = new EntityInsertionAdapter<Alerts>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Alerts`(`alert_id`,`alert_date`,`alert_priority`,`alert_enddate`,`alert_sub`,`alert_message`,`student_id`,`active`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Alerts value) {
        if (value.getAlertId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getAlertId());
        }
        if (value.getAlertDate() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getAlertDate());
        }
        if (value.getAlertPriority() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAlertPriority());
        }
        if (value.getAlertEndDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getAlertEndDate());
        }
        if (value.getAlertSub() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAlertSub());
        }
        if (value.getAlertMsg() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getAlertMsg());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStudentId());
        }
        if (value.getActive() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getActive());
        }
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Alerts";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUserAlerts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Alerts where student_id= ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Alerts... alerts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAlerts.insert(alerts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int DeleteToken() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteToken.acquire();
    __db.beginTransaction();
    try {
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteToken.release(_stmt);
    }
  }

  @Override
  public int DeleteUserAlerts(String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserAlerts.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (studentId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, studentId);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteUserAlerts.release(_stmt);
    }
  }

  @Override
  public List<Alerts> getAlerts() {
    final String _sql = "SELECT * FROM Alerts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfAlertId = _cursor.getColumnIndexOrThrow("alert_id");
      final int _cursorIndexOfAlertDate = _cursor.getColumnIndexOrThrow("alert_date");
      final int _cursorIndexOfAlertPriority = _cursor.getColumnIndexOrThrow("alert_priority");
      final int _cursorIndexOfAlertEndDate = _cursor.getColumnIndexOrThrow("alert_enddate");
      final int _cursorIndexOfAlertSub = _cursor.getColumnIndexOrThrow("alert_sub");
      final int _cursorIndexOfAlertMsg = _cursor.getColumnIndexOrThrow("alert_message");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final List<Alerts> _result = new ArrayList<Alerts>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Alerts _item;
        _item = new Alerts();
        final String _tmpAlertId;
        _tmpAlertId = _cursor.getString(_cursorIndexOfAlertId);
        _item.setAlertId(_tmpAlertId);
        final String _tmpAlertDate;
        _tmpAlertDate = _cursor.getString(_cursorIndexOfAlertDate);
        _item.setAlertDate(_tmpAlertDate);
        final String _tmpAlertPriority;
        _tmpAlertPriority = _cursor.getString(_cursorIndexOfAlertPriority);
        _item.setAlertPriority(_tmpAlertPriority);
        final String _tmpAlertEndDate;
        _tmpAlertEndDate = _cursor.getString(_cursorIndexOfAlertEndDate);
        _item.setAlertEndDate(_tmpAlertEndDate);
        final String _tmpAlertSub;
        _tmpAlertSub = _cursor.getString(_cursorIndexOfAlertSub);
        _item.setAlertSub(_tmpAlertSub);
        final String _tmpAlertMsg;
        _tmpAlertMsg = _cursor.getString(_cursorIndexOfAlertMsg);
        _item.setAlertMsg(_tmpAlertMsg);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Alerts> getAlertsStudent(String StudentID) {
    final String _sql = "SELECT * FROM Alerts where student_id= ? order by alert_date desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (StudentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentID);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfAlertId = _cursor.getColumnIndexOrThrow("alert_id");
      final int _cursorIndexOfAlertDate = _cursor.getColumnIndexOrThrow("alert_date");
      final int _cursorIndexOfAlertPriority = _cursor.getColumnIndexOrThrow("alert_priority");
      final int _cursorIndexOfAlertEndDate = _cursor.getColumnIndexOrThrow("alert_enddate");
      final int _cursorIndexOfAlertSub = _cursor.getColumnIndexOrThrow("alert_sub");
      final int _cursorIndexOfAlertMsg = _cursor.getColumnIndexOrThrow("alert_message");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final List<Alerts> _result = new ArrayList<Alerts>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Alerts _item;
        _item = new Alerts();
        final String _tmpAlertId;
        _tmpAlertId = _cursor.getString(_cursorIndexOfAlertId);
        _item.setAlertId(_tmpAlertId);
        final String _tmpAlertDate;
        _tmpAlertDate = _cursor.getString(_cursorIndexOfAlertDate);
        _item.setAlertDate(_tmpAlertDate);
        final String _tmpAlertPriority;
        _tmpAlertPriority = _cursor.getString(_cursorIndexOfAlertPriority);
        _item.setAlertPriority(_tmpAlertPriority);
        final String _tmpAlertEndDate;
        _tmpAlertEndDate = _cursor.getString(_cursorIndexOfAlertEndDate);
        _item.setAlertEndDate(_tmpAlertEndDate);
        final String _tmpAlertSub;
        _tmpAlertSub = _cursor.getString(_cursorIndexOfAlertSub);
        _item.setAlertSub(_tmpAlertSub);
        final String _tmpAlertMsg;
        _tmpAlertMsg = _cursor.getString(_cursorIndexOfAlertMsg);
        _item.setAlertMsg(_tmpAlertMsg);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAlertCount(String student_id) {
    final String _sql = "SELECT COUNT(alert_id) FROM Alerts where student_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAlertCount() {
    final String _sql = "select count(alert_id) from Alerts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Alerts> getlastStudentIdAlertIds() {
    final String _sql = "select student_id,alert_id,alert_date from Alerts group by student_id  order by alert_date desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfAlertId = _cursor.getColumnIndexOrThrow("alert_id");
      final int _cursorIndexOfAlertDate = _cursor.getColumnIndexOrThrow("alert_date");
      final List<Alerts> _result = new ArrayList<Alerts>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Alerts _item;
        _item = new Alerts();
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpAlertId;
        _tmpAlertId = _cursor.getString(_cursorIndexOfAlertId);
        _item.setAlertId(_tmpAlertId);
        final String _tmpAlertDate;
        _tmpAlertDate = _cursor.getString(_cursorIndexOfAlertDate);
        _item.setAlertDate(_tmpAlertDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
