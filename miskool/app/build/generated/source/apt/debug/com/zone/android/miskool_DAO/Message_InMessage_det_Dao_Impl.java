package com.zone.android.miskool_DAO;

import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Message_In;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Message_InMessage_det_Dao_Impl implements Message_InMessage_det_Dao {
  private final RoomDatabase __db;

  public Message_InMessage_det_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public List<Message_In> fetchMessageDetails() {
    final String _sql = "select * from Message_In";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messagein_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDateOfArrival = _cursor.getColumnIndexOrThrow("message_dateOfArrival");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageTyp = _cursor.getColumnIndexOrThrow("message_type");
      final int _cursorIndexOfMessageDateOfCreated = _cursor.getColumnIndexOrThrow("message_dateCreated");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final int _cursorIndexOfReadFlag = _cursor.getColumnIndexOrThrow("read_flag");
      final List<Message_In> _result = new ArrayList<Message_In>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_In _item;
        _item = new Message_In();
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDateOfArrival;
        _tmpMessageDateOfArrival = _cursor.getString(_cursorIndexOfMessageDateOfArrival);
        _item.setMessageDateOfArrival(_tmpMessageDateOfArrival);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
        final String _tmpMessageTyp;
        _tmpMessageTyp = _cursor.getString(_cursorIndexOfMessageTyp);
        _item.setMessageTyp(_tmpMessageTyp);
        final String _tmpMessageDateOfCreated;
        _tmpMessageDateOfCreated = _cursor.getString(_cursorIndexOfMessageDateOfCreated);
        _item.setMessageDateOfCreated(_tmpMessageDateOfCreated);
        final String _tmpMessageSender;
        _tmpMessageSender = _cursor.getString(_cursorIndexOfMessageSender);
        _item.setMessageSender(_tmpMessageSender);
        final String _tmpMessageReceiver;
        _tmpMessageReceiver = _cursor.getString(_cursorIndexOfMessageReceiver);
        _item.setMessageReceiver(_tmpMessageReceiver);
        final String _tmpReadFlag;
        _tmpReadFlag = _cursor.getString(_cursorIndexOfReadFlag);
        _item.setReadFlag(_tmpReadFlag);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
