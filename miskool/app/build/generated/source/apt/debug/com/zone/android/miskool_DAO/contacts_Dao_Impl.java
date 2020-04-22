package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Contacts;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class contacts_Dao_Impl implements contacts_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfContacts;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  public contacts_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContacts = new EntityInsertionAdapter<Contacts>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Contacts`(`pkey`,`username`,`combName`,`lastname`,`firstname`,`othernames`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contacts value) {
        if (value.getPkey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getPkey());
        }
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getCombName() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getCombName());
        }
        if (value.getLastname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLastname());
        }
        if (value.getFirstname() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFirstname());
        }
        if (value.getOthernames() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getOthernames());
        }
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Contacts";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Contacts... contacts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfContacts.insert(contacts);
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
  public List<Contacts> getAlerts() {
    final String _sql = "SELECT * FROM Contacts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfPkey = _cursor.getColumnIndexOrThrow("pkey");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfCombName = _cursor.getColumnIndexOrThrow("combName");
      final int _cursorIndexOfLastname = _cursor.getColumnIndexOrThrow("lastname");
      final int _cursorIndexOfFirstname = _cursor.getColumnIndexOrThrow("firstname");
      final int _cursorIndexOfOthernames = _cursor.getColumnIndexOrThrow("othernames");
      final List<Contacts> _result = new ArrayList<Contacts>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Contacts _item;
        _item = new Contacts();
        final String _tmpPkey;
        _tmpPkey = _cursor.getString(_cursorIndexOfPkey);
        _item.setPkey(_tmpPkey);
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        _item.setUsername(_tmpUsername);
        final String _tmpCombName;
        _tmpCombName = _cursor.getString(_cursorIndexOfCombName);
        _item.setCombName(_tmpCombName);
        final String _tmpLastname;
        _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
        _item.setLastname(_tmpLastname);
        final String _tmpFirstname;
        _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
        _item.setFirstname(_tmpFirstname);
        final String _tmpOthernames;
        _tmpOthernames = _cursor.getString(_cursorIndexOfOthernames);
        _item.setOthernames(_tmpOthernames);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
