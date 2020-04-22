package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Token_det;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class token_Dao_Impl implements token_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfToken_det;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfToken_det;

  private final SharedSQLiteStatement __preparedStmtOfIncrementToken;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  public token_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfToken_det = new EntityInsertionAdapter<Token_det>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Token_det`(`token_id`,`token`,`token_inc`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Token_det value) {
        stmt.bindLong(1, value.getTokenId());
        if (value.getToken() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getToken());
        }
        stmt.bindLong(3, value.getTokenInc());
      }
    };
    this.__deletionAdapterOfToken_det = new EntityDeletionOrUpdateAdapter<Token_det>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Token_det` WHERE `token_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Token_det value) {
        stmt.bindLong(1, value.getTokenId());
      }
    };
    this.__preparedStmtOfIncrementToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Token_det SET token_inc= ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Token_det";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Token_det... token_dets) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfToken_det.insert(token_dets);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteUsers(Token_det... token_dets) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfToken_det.handleMultiple(token_dets);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int incrementToken(int inc) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementToken.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, inc);
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfIncrementToken.release(_stmt);
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
  public List<Token_det> getAll() {
    final String _sql = "SELECT * FROM Token_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTokenId = _cursor.getColumnIndexOrThrow("token_id");
      final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
      final int _cursorIndexOfTokenInc = _cursor.getColumnIndexOrThrow("token_inc");
      final List<Token_det> _result = new ArrayList<Token_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Token_det _item;
        _item = new Token_det();
        final int _tmpTokenId;
        _tmpTokenId = _cursor.getInt(_cursorIndexOfTokenId);
        _item.setTokenId(_tmpTokenId);
        final String _tmpToken;
        _tmpToken = _cursor.getString(_cursorIndexOfToken);
        _item.setToken(_tmpToken);
        final int _tmpTokenInc;
        _tmpTokenInc = _cursor.getInt(_cursorIndexOfTokenInc);
        _item.setTokenInc(_tmpTokenInc);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Token_det> getTokenDetails() {
    final String _sql = "SELECT * FROM Token_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfTokenId = _cursor.getColumnIndexOrThrow("token_id");
      final int _cursorIndexOfToken = _cursor.getColumnIndexOrThrow("token");
      final int _cursorIndexOfTokenInc = _cursor.getColumnIndexOrThrow("token_inc");
      final List<Token_det> _result = new ArrayList<Token_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Token_det _item;
        _item = new Token_det();
        final int _tmpTokenId;
        _tmpTokenId = _cursor.getInt(_cursorIndexOfTokenId);
        _item.setTokenId(_tmpTokenId);
        final String _tmpToken;
        _tmpToken = _cursor.getString(_cursorIndexOfToken);
        _item.setToken(_tmpToken);
        final int _tmpTokenInc;
        _tmpTokenInc = _cursor.getInt(_cursorIndexOfTokenInc);
        _item.setTokenInc(_tmpTokenInc);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
