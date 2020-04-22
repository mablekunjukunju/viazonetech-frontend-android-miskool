package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Person_det;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class person_Dao_Impl implements person_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPerson_det;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  public person_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPerson_det = new EntityInsertionAdapter<Person_det>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Person_det`(`student_id`,`first_name`,`identitiy`,`last_name`) VALUES (?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Person_det value) {
        if (value.getStudentId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getStudentId());
        }
        if (value.getFirstName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFirstName());
        }
        if (value.getIdentitiy() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getIdentitiy());
        }
        if (value.getLastName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLastName());
        }
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Person_det";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Person_det... person_dets) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPerson_det.insert(person_dets);
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
  public List<Person_det> getPersonDetails() {
    final String _sql = "SELECT * FROM Person_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfFirstName = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfIdentitiy = _cursor.getColumnIndexOrThrow("identitiy");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("last_name");
      final List<Person_det> _result = new ArrayList<Person_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Person_det _item;
        _item = new Person_det();
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpFirstName;
        _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        _item.setFirstName(_tmpFirstName);
        final String _tmpIdentitiy;
        _tmpIdentitiy = _cursor.getString(_cursorIndexOfIdentitiy);
        _item.setIdentitiy(_tmpIdentitiy);
        final String _tmpLastName;
        _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        _item.setLastName(_tmpLastName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Person_det> getPersonDetailsStudent(String StudentID) {
    final String _sql = "SELECT * FROM Person_det where student_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (StudentID == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, StudentID);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfFirstName = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfIdentitiy = _cursor.getColumnIndexOrThrow("identitiy");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("last_name");
      final List<Person_det> _result = new ArrayList<Person_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Person_det _item;
        _item = new Person_det();
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpFirstName;
        _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        _item.setFirstName(_tmpFirstName);
        final String _tmpIdentitiy;
        _tmpIdentitiy = _cursor.getString(_cursorIndexOfIdentitiy);
        _item.setIdentitiy(_tmpIdentitiy);
        final String _tmpLastName;
        _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        _item.setLastName(_tmpLastName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getStudentCount() {
    final String _sql = "select count(student_id) from Person_det";
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
  public String getlastStudentId() {
    final String _sql = "select student_id from Person_det where 'rowid' = (select max('rowid') from Person_det)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final String _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getString(0);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Person_det> getDefaultStudent() {
    final String _sql = "SELECT * FROM Person_det  LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfFirstName = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfIdentitiy = _cursor.getColumnIndexOrThrow("identitiy");
      final int _cursorIndexOfLastName = _cursor.getColumnIndexOrThrow("last_name");
      final List<Person_det> _result = new ArrayList<Person_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Person_det _item;
        _item = new Person_det();
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpFirstName;
        _tmpFirstName = _cursor.getString(_cursorIndexOfFirstName);
        _item.setFirstName(_tmpFirstName);
        final String _tmpIdentitiy;
        _tmpIdentitiy = _cursor.getString(_cursorIndexOfIdentitiy);
        _item.setIdentitiy(_tmpIdentitiy);
        final String _tmpLastName;
        _tmpLastName = _cursor.getString(_cursorIndexOfLastName);
        _item.setLastName(_tmpLastName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
