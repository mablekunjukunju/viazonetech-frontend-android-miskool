package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Message_det;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class messagedet_Dao_Impl implements messagedet_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMessage_det;

  public messagedet_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage_det = new EntityInsertionAdapter<Message_det>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message_det`(`instance_id`,`msg_id`,`messages`,`message_type`,`message_timeRecent`,`message_sender`,`message_receiver`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message_det value) {
        if (value.getInstanceId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getInstanceId());
        }
        if (value.getMsgId() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getMsgId());
        }
        if (value.getMessages() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMessages());
        }
        if (value.getMessageTyp() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageTyp());
        }
        if (value.getMessageTimRec() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessageTimRec());
        }
        if (value.getMessageSender() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessageSender());
        }
        if (value.getMessageReceiver() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessageReceiver());
        }
      }
    };
  }

  @Override
  public void insertAll(Message_det... messages) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage_det.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Message_det> getAll() {
    final String _sql = "SELECT * FROM Message_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfInstanceId = _cursor.getColumnIndexOrThrow("instance_id");
      final int _cursorIndexOfMsgId = _cursor.getColumnIndexOrThrow("msg_id");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageTyp = _cursor.getColumnIndexOrThrow("message_type");
      final int _cursorIndexOfMessageTimRec = _cursor.getColumnIndexOrThrow("message_timeRecent");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message_det> _result = new ArrayList<Message_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_det _item;
        _item = new Message_det();
        final String _tmpInstanceId;
        _tmpInstanceId = _cursor.getString(_cursorIndexOfInstanceId);
        _item.setInstanceId(_tmpInstanceId);
        final String _tmpMsgId;
        _tmpMsgId = _cursor.getString(_cursorIndexOfMsgId);
        _item.setMsgId(_tmpMsgId);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
        final String _tmpMessageTyp;
        _tmpMessageTyp = _cursor.getString(_cursorIndexOfMessageTyp);
        _item.setMessageTyp(_tmpMessageTyp);
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
  public List<Message_det> getAllMessages() {
    final String _sql = "SELECT * FROM Message_det";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfInstanceId = _cursor.getColumnIndexOrThrow("instance_id");
      final int _cursorIndexOfMsgId = _cursor.getColumnIndexOrThrow("msg_id");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageTyp = _cursor.getColumnIndexOrThrow("message_type");
      final int _cursorIndexOfMessageTimRec = _cursor.getColumnIndexOrThrow("message_timeRecent");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message_det> _result = new ArrayList<Message_det>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_det _item;
        _item = new Message_det();
        final String _tmpInstanceId;
        _tmpInstanceId = _cursor.getString(_cursorIndexOfInstanceId);
        _item.setInstanceId(_tmpInstanceId);
        final String _tmpMsgId;
        _tmpMsgId = _cursor.getString(_cursorIndexOfMsgId);
        _item.setMsgId(_tmpMsgId);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
        final String _tmpMessageTyp;
        _tmpMessageTyp = _cursor.getString(_cursorIndexOfMessageTyp);
        _item.setMessageTyp(_tmpMessageTyp);
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
