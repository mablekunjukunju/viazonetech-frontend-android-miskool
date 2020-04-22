package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Message_Out;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class messageOut_Dao_Impl implements messageOut_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMessage_Out;

  public messageOut_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage_Out = new EntityInsertionAdapter<Message_Out>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message_Out`(`out_id`,`student_id`,`message_date`,`message_sub`,`message_content`,`message_receiver`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message_Out value) {
        if (value.getOutId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getOutId());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getStudentId());
        }
        if (value.getMessageDate() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMessageDate());
        }
        if (value.getMessageSub() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageSub());
        }
        if (value.getMessageContent() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessageContent());
        }
        if (value.getMessageReceiver() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessageReceiver());
        }
      }
    };
  }

  @Override
  public void insertAll(Message_Out... messages) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage_Out.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Message_Out> getAll() {
    final String _sql = "SELECT * FROM Message_Out";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfOutId = _cursor.getColumnIndexOrThrow("out_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDate = _cursor.getColumnIndexOrThrow("message_date");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfMessageContent = _cursor.getColumnIndexOrThrow("message_content");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message_Out> _result = new ArrayList<Message_Out>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_Out _item;
        _item = new Message_Out();
        final String _tmpOutId;
        _tmpOutId = _cursor.getString(_cursorIndexOfOutId);
        _item.setOutId(_tmpOutId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDate;
        _tmpMessageDate = _cursor.getString(_cursorIndexOfMessageDate);
        _item.setMessageDate(_tmpMessageDate);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpMessageContent;
        _tmpMessageContent = _cursor.getString(_cursorIndexOfMessageContent);
        _item.setMessageContent(_tmpMessageContent);
        final String _tmpMessageReceiver;
        _tmpMessageReceiver = _cursor.getString(_cursorIndexOfMessageReceiver);
        _item.setMessageReceiver(_tmpMessageReceiver);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Message_Out> getAllMessages() {
    final String _sql = "SELECT * FROM Message_Out";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfOutId = _cursor.getColumnIndexOrThrow("out_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDate = _cursor.getColumnIndexOrThrow("message_date");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfMessageContent = _cursor.getColumnIndexOrThrow("message_content");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message_Out> _result = new ArrayList<Message_Out>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_Out _item;
        _item = new Message_Out();
        final String _tmpOutId;
        _tmpOutId = _cursor.getString(_cursorIndexOfOutId);
        _item.setOutId(_tmpOutId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDate;
        _tmpMessageDate = _cursor.getString(_cursorIndexOfMessageDate);
        _item.setMessageDate(_tmpMessageDate);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpMessageContent;
        _tmpMessageContent = _cursor.getString(_cursorIndexOfMessageContent);
        _item.setMessageContent(_tmpMessageContent);
        final String _tmpMessageReceiver;
        _tmpMessageReceiver = _cursor.getString(_cursorIndexOfMessageReceiver);
        _item.setMessageReceiver(_tmpMessageReceiver);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int getMaxMessageOutID() {
    final String _sql = "SELECT MAX(student_id) FROM Message_Out";
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
}
