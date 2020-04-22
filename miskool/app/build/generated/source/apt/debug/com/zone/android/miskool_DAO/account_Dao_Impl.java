package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Account;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class account_Dao_Impl implements account_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAccount;

  public account_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccount = new EntityInsertionAdapter<Account>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Account`(`person_id`,`person_name`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Account value) {
        if (value.getPersonId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPersonId());
        }
        if (value.getPersonName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getPersonName());
        }
      }
    };
  }

  @Override
  public void insertAll(Account... accounts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAccount.insert(accounts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Account> getAccountDetails() {
    final String _sql = "SELECT * FROM Account";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfPersonId = _cursor.getColumnIndexOrThrow("person_id");
      final int _cursorIndexOfPersonName = _cursor.getColumnIndexOrThrow("person_name");
      final List<Account> _result = new ArrayList<Account>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Account _item;
        _item = new Account();
        final String _tmpPersonId;
        _tmpPersonId = _cursor.getString(_cursorIndexOfPersonId);
        _item.setPersonId(_tmpPersonId);
        final String _tmpPersonName;
        _tmpPersonName = _cursor.getString(_cursorIndexOfPersonName);
        _item.setPersonName(_tmpPersonName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getAccountCount() {
    final String _sql = "select count(person_id) from Account";
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
  public String getAccountId() {
    final String _sql = "select person_id from Account where 'rowid' = (select max('rowid') from Account)";
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
}
