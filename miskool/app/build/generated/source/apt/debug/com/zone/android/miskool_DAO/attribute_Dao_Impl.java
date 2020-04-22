package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Attributes;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class attribute_Dao_Impl implements attribute_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAttributes;

  private final SharedSQLiteStatement __preparedStmtOfDeleteSequence;

  public attribute_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAttributes = new EntityInsertionAdapter<Attributes>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Attributes`(`rowwwid`,`studentId`,`attName`,`sttValue`,`studentName`) VALUES (?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Attributes value) {
        if (value.getRowwwid() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRowwwid());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStudentId());
        }
        if (value.getAttName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getAttName());
        }
        if (value.getSttValue() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getSttValue());
        }
        if (value.getStudentName() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStudentName());
        }
      }
    };
    this.__preparedStmtOfDeleteSequence = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Attributes";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Attributes... atts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAttributes.insert(atts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteSequence() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteSequence.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteSequence.release(_stmt);
    }
  }

  @Override
  public List<Attributes> getAtts() {
    final String _sql = "SELECT * FROM Attributes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowwwid = _cursor.getColumnIndexOrThrow("rowwwid");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("studentId");
      final int _cursorIndexOfAttName = _cursor.getColumnIndexOrThrow("attName");
      final int _cursorIndexOfSttValue = _cursor.getColumnIndexOrThrow("sttValue");
      final int _cursorIndexOfStudentName = _cursor.getColumnIndexOrThrow("studentName");
      final List<Attributes> _result = new ArrayList<Attributes>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Attributes _item;
        _item = new Attributes();
        final String _tmpRowwwid;
        _tmpRowwwid = _cursor.getString(_cursorIndexOfRowwwid);
        _item.setRowwwid(_tmpRowwwid);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpAttName;
        _tmpAttName = _cursor.getString(_cursorIndexOfAttName);
        _item.setAttName(_tmpAttName);
        final String _tmpSttValue;
        _tmpSttValue = _cursor.getString(_cursorIndexOfSttValue);
        _item.setSttValue(_tmpSttValue);
        final String _tmpStudentName;
        _tmpStudentName = _cursor.getString(_cursorIndexOfStudentName);
        _item.setStudentName(_tmpStudentName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Attributes> getAttsStudent(String StudentID) {
    final String _sql = "SELECT * FROM Attributes where studentId= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (StudentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentID);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRowwwid = _cursor.getColumnIndexOrThrow("rowwwid");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("studentId");
      final int _cursorIndexOfAttName = _cursor.getColumnIndexOrThrow("attName");
      final int _cursorIndexOfSttValue = _cursor.getColumnIndexOrThrow("sttValue");
      final int _cursorIndexOfStudentName = _cursor.getColumnIndexOrThrow("studentName");
      final List<Attributes> _result = new ArrayList<Attributes>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Attributes _item;
        _item = new Attributes();
        final String _tmpRowwwid;
        _tmpRowwwid = _cursor.getString(_cursorIndexOfRowwwid);
        _item.setRowwwid(_tmpRowwwid);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpAttName;
        _tmpAttName = _cursor.getString(_cursorIndexOfAttName);
        _item.setAttName(_tmpAttName);
        final String _tmpSttValue;
        _tmpSttValue = _cursor.getString(_cursorIndexOfSttValue);
        _item.setSttValue(_tmpSttValue);
        final String _tmpStudentName;
        _tmpStudentName = _cursor.getString(_cursorIndexOfStudentName);
        _item.setStudentName(_tmpStudentName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
