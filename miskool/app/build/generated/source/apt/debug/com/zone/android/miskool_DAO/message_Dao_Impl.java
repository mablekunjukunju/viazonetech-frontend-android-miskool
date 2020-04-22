package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Message;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class message_Dao_Impl implements message_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMessage;

  public message_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message`(`messageIn_id`,`thread_id`,`student_id`,`message_created`,`message_type`,`messages`,`message_sub`,`message_timeRecent`,`message_sender`,`message_receiver`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message value) {
        if (value.getMessageInId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getMessageInId());
        }
        if (value.getThreadId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getThreadId());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getStudentId());
        }
        if (value.getMessageCreated() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageCreated());
        }
        if (value.getMessageType() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessageType());
        }
        if (value.getMessages() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessages());
        }
        if (value.getMessageSub() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessageSub());
        }
        if (value.getMessageTimRec() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMessageTimRec());
        }
        if (value.getMessageSender() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getMessageSender());
        }
        if (value.getMessageReceiver() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getMessageReceiver());
        }
      }
    };
  }

  @Override
  public void insertAll(Message... messages) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Message> getAll() {
    final String _sql = "SELECT * FROM Message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messageIn_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageCreated = _cursor.getColumnIndexOrThrow("message_created");
      final int _cursorIndexOfMessageType = _cursor.getColumnIndexOrThrow("message_type");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfMessageTimRec = _cursor.getColumnIndexOrThrow("message_timeRecent");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageCreated;
        _tmpMessageCreated = _cursor.getString(_cursorIndexOfMessageCreated);
        _item.setMessageCreated(_tmpMessageCreated);
        final String _tmpMessageType;
        _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        _item.setMessageType(_tmpMessageType);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpMessageTimRec;
        _tmpMessageTimRec = _cursor.getString(_cursorIndexOfMessageTimRec);
        _item.setMessageTimRec(_tmpMessageTimRec);
        final String _tmpMessageSender;
        _tmpMessageSender = _cursor.getString(_cursorIndexOfMessageSender);
        _item.setMessageSender(_tmpMessageSender);
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
  public List<Message> getAllMessages() {
    final String _sql = "SELECT * FROM Message";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messageIn_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageCreated = _cursor.getColumnIndexOrThrow("message_created");
      final int _cursorIndexOfMessageType = _cursor.getColumnIndexOrThrow("message_type");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfMessageTimRec = _cursor.getColumnIndexOrThrow("message_timeRecent");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message _item;
        _item = new Message();
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageCreated;
        _tmpMessageCreated = _cursor.getString(_cursorIndexOfMessageCreated);
        _item.setMessageCreated(_tmpMessageCreated);
        final String _tmpMessageType;
        _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
        _item.setMessageType(_tmpMessageType);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpMessageTimRec;
        _tmpMessageTimRec = _cursor.getString(_cursorIndexOfMessageTimRec);
        _item.setMessageTimRec(_tmpMessageTimRec);
        final String _tmpMessageSender;
        _tmpMessageSender = _cursor.getString(_cursorIndexOfMessageSender);
        _item.setMessageSender(_tmpMessageSender);
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
}
