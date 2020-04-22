package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Schedule;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class schedule_Dao_Impl implements schedule_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSchedule;

  private final EntityInsertionAdapter __insertionAdapterOfSchedule_1;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSchedule;

  private final SharedSQLiteStatement __preparedStmtOfDeleteScheduleAllUser;

  public schedule_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSchedule = new EntityInsertionAdapter<Schedule>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Schedule`(`schedule_id`,`student_id`,`starttime_null`,`endtime_null`,`pkey`,`starttime`,`endtime`,`active`,`subject`,`text`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Schedule value) {
        if (value.getScheduleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getScheduleId());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStudentId());
        }
        if (value.getStarttimeNull() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStarttimeNull());
        }
        if (value.getEndtimeNull() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEndtimeNull());
        }
        if (value.getPkey() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPkey());
        }
        if (value.getStarttime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStarttime());
        }
        if (value.getEndtime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEndtime());
        }
        if (value.getActive() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getActive());
        }
        if (value.getSubject() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getSubject());
        }
        if (value.getText() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getText());
        }
      }
    };
    this.__insertionAdapterOfSchedule_1 = new EntityInsertionAdapter<Schedule>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Schedule`(`schedule_id`,`student_id`,`starttime_null`,`endtime_null`,`pkey`,`starttime`,`endtime`,`active`,`subject`,`text`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Schedule value) {
        if (value.getScheduleId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getScheduleId());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStudentId());
        }
        if (value.getStarttimeNull() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStarttimeNull());
        }
        if (value.getEndtimeNull() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getEndtimeNull());
        }
        if (value.getPkey() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPkey());
        }
        if (value.getStarttime() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStarttime());
        }
        if (value.getEndtime() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getEndtime());
        }
        if (value.getActive() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getActive());
        }
        if (value.getSubject() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getSubject());
        }
        if (value.getText() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getText());
        }
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Schedule";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteSchedule = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Schedule where pkey = ? and student_id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteScheduleAllUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Schedule where student_id= ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Schedule... schedules) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSchedule.insert(schedules);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertOnConflict(Schedule... schedules) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSchedule_1.insert(schedules);
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
  public int DeleteSchedule(String p_key, String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSchedule.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (p_key == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, p_key);
      }
      _argIndex = 2;
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
      __preparedStmtOfDeleteSchedule.release(_stmt);
    }
  }

  @Override
  public int DeleteScheduleAllUser(String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteScheduleAllUser.acquire();
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
      __preparedStmtOfDeleteScheduleAllUser.release(_stmt);
    }
  }

  @Override
  public List<Schedule> getSchedulesStudent(String studentId) {
    final String _sql = "SELECT * FROM Schedule where student_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfScheduleId = _cursor.getColumnIndexOrThrow("schedule_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStarttimeNull = _cursor.getColumnIndexOrThrow("starttime_null");
      final int _cursorIndexOfEndtimeNull = _cursor.getColumnIndexOrThrow("endtime_null");
      final int _cursorIndexOfPkey = _cursor.getColumnIndexOrThrow("pkey");
      final int _cursorIndexOfStarttime = _cursor.getColumnIndexOrThrow("starttime");
      final int _cursorIndexOfEndtime = _cursor.getColumnIndexOrThrow("endtime");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfSubject = _cursor.getColumnIndexOrThrow("subject");
      final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
      final List<Schedule> _result = new ArrayList<Schedule>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Schedule _item;
        _item = new Schedule();
        final String _tmpScheduleId;
        _tmpScheduleId = _cursor.getString(_cursorIndexOfScheduleId);
        _item.setScheduleId(_tmpScheduleId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpStarttimeNull;
        _tmpStarttimeNull = _cursor.getString(_cursorIndexOfStarttimeNull);
        _item.setStarttimeNull(_tmpStarttimeNull);
        final String _tmpEndtimeNull;
        _tmpEndtimeNull = _cursor.getString(_cursorIndexOfEndtimeNull);
        _item.setEndtimeNull(_tmpEndtimeNull);
        final String _tmpPkey;
        _tmpPkey = _cursor.getString(_cursorIndexOfPkey);
        _item.setPkey(_tmpPkey);
        final String _tmpStarttime;
        _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        _item.setStarttime(_tmpStarttime);
        final String _tmpEndtime;
        _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        _item.setEndtime(_tmpEndtime);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        final String _tmpSubject;
        _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
        _item.setSubject(_tmpSubject);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getScheduleCount(String student_id) {
    final String _sql = "SELECT COUNT(pkey) FROM Schedule where student_id = ?";
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
  public List<Schedule> getActiveSchedules(String active, String Active, String studentId) {
    final String _sql = "SELECT * FROM Schedule where student_id = ? and active = ? or active = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    _argIndex = 2;
    if (active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, active);
    }
    _argIndex = 3;
    if (Active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, Active);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfScheduleId = _cursor.getColumnIndexOrThrow("schedule_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStarttimeNull = _cursor.getColumnIndexOrThrow("starttime_null");
      final int _cursorIndexOfEndtimeNull = _cursor.getColumnIndexOrThrow("endtime_null");
      final int _cursorIndexOfPkey = _cursor.getColumnIndexOrThrow("pkey");
      final int _cursorIndexOfStarttime = _cursor.getColumnIndexOrThrow("starttime");
      final int _cursorIndexOfEndtime = _cursor.getColumnIndexOrThrow("endtime");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfSubject = _cursor.getColumnIndexOrThrow("subject");
      final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
      final List<Schedule> _result = new ArrayList<Schedule>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Schedule _item;
        _item = new Schedule();
        final String _tmpScheduleId;
        _tmpScheduleId = _cursor.getString(_cursorIndexOfScheduleId);
        _item.setScheduleId(_tmpScheduleId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpStarttimeNull;
        _tmpStarttimeNull = _cursor.getString(_cursorIndexOfStarttimeNull);
        _item.setStarttimeNull(_tmpStarttimeNull);
        final String _tmpEndtimeNull;
        _tmpEndtimeNull = _cursor.getString(_cursorIndexOfEndtimeNull);
        _item.setEndtimeNull(_tmpEndtimeNull);
        final String _tmpPkey;
        _tmpPkey = _cursor.getString(_cursorIndexOfPkey);
        _item.setPkey(_tmpPkey);
        final String _tmpStarttime;
        _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        _item.setStarttime(_tmpStarttime);
        final String _tmpEndtime;
        _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        _item.setEndtime(_tmpEndtime);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        final String _tmpSubject;
        _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
        _item.setSubject(_tmpSubject);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Schedule> getSelEvents(String active, String Active, String studentId, String date) {
    final String _sql = "SELECT * FROM Schedule where `endtime_null` >= ? and `starttime_null` <= ? and student_id = ? and active = ? or active = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 3;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    _argIndex = 4;
    if (active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, active);
    }
    _argIndex = 5;
    if (Active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, Active);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfScheduleId = _cursor.getColumnIndexOrThrow("schedule_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStarttimeNull = _cursor.getColumnIndexOrThrow("starttime_null");
      final int _cursorIndexOfEndtimeNull = _cursor.getColumnIndexOrThrow("endtime_null");
      final int _cursorIndexOfPkey = _cursor.getColumnIndexOrThrow("pkey");
      final int _cursorIndexOfStarttime = _cursor.getColumnIndexOrThrow("starttime");
      final int _cursorIndexOfEndtime = _cursor.getColumnIndexOrThrow("endtime");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfSubject = _cursor.getColumnIndexOrThrow("subject");
      final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
      final List<Schedule> _result = new ArrayList<Schedule>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Schedule _item;
        _item = new Schedule();
        final String _tmpScheduleId;
        _tmpScheduleId = _cursor.getString(_cursorIndexOfScheduleId);
        _item.setScheduleId(_tmpScheduleId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpStarttimeNull;
        _tmpStarttimeNull = _cursor.getString(_cursorIndexOfStarttimeNull);
        _item.setStarttimeNull(_tmpStarttimeNull);
        final String _tmpEndtimeNull;
        _tmpEndtimeNull = _cursor.getString(_cursorIndexOfEndtimeNull);
        _item.setEndtimeNull(_tmpEndtimeNull);
        final String _tmpPkey;
        _tmpPkey = _cursor.getString(_cursorIndexOfPkey);
        _item.setPkey(_tmpPkey);
        final String _tmpStarttime;
        _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        _item.setStarttime(_tmpStarttime);
        final String _tmpEndtime;
        _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        _item.setEndtime(_tmpEndtime);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        final String _tmpSubject;
        _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
        _item.setSubject(_tmpSubject);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Schedule> getSelEventTime(String active, String Active, String studentId,
      String date) {
    final String _sql = "SELECT * FROM Schedule where `endtime` >= ? and `starttime` <= ? and student_id = ? and active = ? or active = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 2;
    if (date == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, date);
    }
    _argIndex = 3;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    _argIndex = 4;
    if (active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, active);
    }
    _argIndex = 5;
    if (Active == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, Active);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfScheduleId = _cursor.getColumnIndexOrThrow("schedule_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStarttimeNull = _cursor.getColumnIndexOrThrow("starttime_null");
      final int _cursorIndexOfEndtimeNull = _cursor.getColumnIndexOrThrow("endtime_null");
      final int _cursorIndexOfPkey = _cursor.getColumnIndexOrThrow("pkey");
      final int _cursorIndexOfStarttime = _cursor.getColumnIndexOrThrow("starttime");
      final int _cursorIndexOfEndtime = _cursor.getColumnIndexOrThrow("endtime");
      final int _cursorIndexOfActive = _cursor.getColumnIndexOrThrow("active");
      final int _cursorIndexOfSubject = _cursor.getColumnIndexOrThrow("subject");
      final int _cursorIndexOfText = _cursor.getColumnIndexOrThrow("text");
      final List<Schedule> _result = new ArrayList<Schedule>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Schedule _item;
        _item = new Schedule();
        final String _tmpScheduleId;
        _tmpScheduleId = _cursor.getString(_cursorIndexOfScheduleId);
        _item.setScheduleId(_tmpScheduleId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpStarttimeNull;
        _tmpStarttimeNull = _cursor.getString(_cursorIndexOfStarttimeNull);
        _item.setStarttimeNull(_tmpStarttimeNull);
        final String _tmpEndtimeNull;
        _tmpEndtimeNull = _cursor.getString(_cursorIndexOfEndtimeNull);
        _item.setEndtimeNull(_tmpEndtimeNull);
        final String _tmpPkey;
        _tmpPkey = _cursor.getString(_cursorIndexOfPkey);
        _item.setPkey(_tmpPkey);
        final String _tmpStarttime;
        _tmpStarttime = _cursor.getString(_cursorIndexOfStarttime);
        _item.setStarttime(_tmpStarttime);
        final String _tmpEndtime;
        _tmpEndtime = _cursor.getString(_cursorIndexOfEndtime);
        _item.setEndtime(_tmpEndtime);
        final String _tmpActive;
        _tmpActive = _cursor.getString(_cursorIndexOfActive);
        _item.setActive(_tmpActive);
        final String _tmpSubject;
        _tmpSubject = _cursor.getString(_cursorIndexOfSubject);
        _item.setSubject(_tmpSubject);
        final String _tmpText;
        _tmpText = _cursor.getString(_cursorIndexOfText);
        _item.setText(_tmpText);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
