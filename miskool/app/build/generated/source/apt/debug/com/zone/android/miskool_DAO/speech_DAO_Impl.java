package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Speech;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class speech_DAO_Impl implements speech_DAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfSpeech;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserSender;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  public speech_DAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSpeech = new EntityInsertionAdapter<Speech>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Speech`(`speech_id`,`thread_id`,`student_id`,`message_date`,`messages`,`message_sender`,`message_receiver`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Speech value) {
        stmt.bindLong(1, value.getSpeechId());
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
        if (value.getMessageDate() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageDate());
        }
        if (value.getMessages() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessages());
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
    this.__preparedStmtOfDeleteUserSender = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Speech where student_id= ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Speech";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Speech... speeches) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfSpeech.insert(speeches);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public int DeleteUserSender(String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserSender.acquire();
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
      __preparedStmtOfDeleteUserSender.release(_stmt);
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
  public List<Speech> getAll() {
    final String _sql = "SELECT * FROM Speech";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfSpeechId = _cursor.getColumnIndexOrThrow("speech_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDate = _cursor.getColumnIndexOrThrow("message_date");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Speech> _result = new ArrayList<Speech>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Speech _item;
        _item = new Speech();
        final int _tmpSpeechId;
        _tmpSpeechId = _cursor.getInt(_cursorIndexOfSpeechId);
        _item.setSpeechId(_tmpSpeechId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDate;
        _tmpMessageDate = _cursor.getString(_cursorIndexOfMessageDate);
        _item.setMessageDate(_tmpMessageDate);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
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
  public List<Speech> getAllMessagesStudent(String student_id) {
    final String _sql = "SELECT * FROM Speech where student_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfSpeechId = _cursor.getColumnIndexOrThrow("speech_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDate = _cursor.getColumnIndexOrThrow("message_date");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Speech> _result = new ArrayList<Speech>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Speech _item;
        _item = new Speech();
        final int _tmpSpeechId;
        _tmpSpeechId = _cursor.getInt(_cursorIndexOfSpeechId);
        _item.setSpeechId(_tmpSpeechId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDate;
        _tmpMessageDate = _cursor.getString(_cursorIndexOfMessageDate);
        _item.setMessageDate(_tmpMessageDate);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
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
  public int getMessageCunt(String student_id) {
    final String _sql = "SELECT COUNT(speech_id) FROM Speech where message_receiver= ?";
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
  public List<Speech> getInboxMessagesStudentThread(String student_id) {
    final String _sql = "SELECT * FROM Speech where student_id= ? order by message_date asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfSpeechId = _cursor.getColumnIndexOrThrow("speech_id");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDate = _cursor.getColumnIndexOrThrow("message_date");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Speech> _result = new ArrayList<Speech>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Speech _item;
        _item = new Speech();
        final int _tmpSpeechId;
        _tmpSpeechId = _cursor.getInt(_cursorIndexOfSpeechId);
        _item.setSpeechId(_tmpSpeechId);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDate;
        _tmpMessageDate = _cursor.getString(_cursorIndexOfMessageDate);
        _item.setMessageDate(_tmpMessageDate);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
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
