package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Message_In;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class messageIn_Dao_Impl implements messageIn_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfMessage_In;

  private final EntityInsertionAdapter __insertionAdapterOfMessage_In_1;

  private final SharedSQLiteStatement __preparedStmtOfUpdateFlag;

  private final SharedSQLiteStatement __preparedStmtOfUpdateReadFlag;

  private final SharedSQLiteStatement __preparedStmtOfDeleteToken;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMessageId;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMessageAllUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserReceive;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserSender;

  public messageIn_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage_In = new EntityInsertionAdapter<Message_In>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Message_In`(`messagein_id`,`thread_id`,`student_id`,`message_dateOfArrival`,`message_sub`,`messages`,`message_type`,`message_dateCreated`,`message_sender`,`message_receiver`,`read_flag`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message_In value) {
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
        if (value.getMessageDateOfArrival() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageDateOfArrival());
        }
        if (value.getMessageSub() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessageSub());
        }
        if (value.getMessages() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessages());
        }
        if (value.getMessageTyp() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessageTyp());
        }
        if (value.getMessageDateOfCreated() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMessageDateOfCreated());
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
        if (value.getReadFlag() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReadFlag());
        }
      }
    };
    this.__insertionAdapterOfMessage_In_1 = new EntityInsertionAdapter<Message_In>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Message_In`(`messagein_id`,`thread_id`,`student_id`,`message_dateOfArrival`,`message_sub`,`messages`,`message_type`,`message_dateCreated`,`message_sender`,`message_receiver`,`read_flag`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Message_In value) {
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
        if (value.getMessageDateOfArrival() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getMessageDateOfArrival());
        }
        if (value.getMessageSub() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getMessageSub());
        }
        if (value.getMessages() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMessages());
        }
        if (value.getMessageTyp() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getMessageTyp());
        }
        if (value.getMessageDateOfCreated() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getMessageDateOfCreated());
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
        if (value.getReadFlag() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getReadFlag());
        }
      }
    };
    this.__preparedStmtOfUpdateFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Message_In SET read_flag = 1 where message_receiver= ? and thread_id= ? ";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateReadFlag = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Message_In SET read_flag = ? where message_receiver= ? and messagein_id= ? ";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteToken = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message_In";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMessageId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message_In where messagein_id= ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMessageAllUser = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message_In where student_id= ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUserReceive = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message_In where message_receiver= ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUserSender = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Message_In where message_sender= ?";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(Message_In... messages) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage_In.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insert(Message_In... messages) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfMessage_In_1.insert(messages);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateFlag(String student_id, String Thread_id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateFlag.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (student_id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, student_id);
      }
      _argIndex = 2;
      if (Thread_id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, Thread_id);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateFlag.release(_stmt);
    }
  }

  @Override
  public void updateReadFlag(String student_id, String messagein_id, String read_flag) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateReadFlag.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (read_flag == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, read_flag);
      }
      _argIndex = 2;
      if (student_id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, student_id);
      }
      _argIndex = 3;
      if (messagein_id == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, messagein_id);
      }
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateReadFlag.release(_stmt);
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
  public int DeleteMessageId(String messageId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMessageId.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      if (messageId == null) {
        _stmt.bindNull(_argIndex);
      } else {
        _stmt.bindString(_argIndex, messageId);
      }
      final int _result = _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteMessageId.release(_stmt);
    }
  }

  @Override
  public int DeleteMessageAllUser(String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMessageAllUser.acquire();
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
      __preparedStmtOfDeleteMessageAllUser.release(_stmt);
    }
  }

  @Override
  public int DeleteUserReceive(String studentId) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserReceive.acquire();
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
      __preparedStmtOfDeleteUserReceive.release(_stmt);
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
  public List<Message_In> getAll() {
    final String _sql = "SELECT * FROM Message_In";
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

  @Override
  public List<Message_In> getAllMessagesStudent(String student_id) {
    final String _sql = "SELECT * FROM Message_In where student_id= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
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

  @Override
  public int getMessageCunt(String student_id) {
    final String _sql = "SELECT COUNT(messagein_id) FROM Message_In where message_receiver= ?";
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
  public List<Message_In> getInboxMessagesStudent(String student_id) {
    final String _sql = "SELECT message_dateCreated,message_sub,thread_id ,messagein_id,messages,message_sender,message_receiver,read_flag FROM Message_In where message_receiver= ? order by message_dateCreated desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageDateOfCreated = _cursor.getColumnIndexOrThrow("message_dateCreated");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messagein_id");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final int _cursorIndexOfReadFlag = _cursor.getColumnIndexOrThrow("read_flag");
      final List<Message_In> _result = new ArrayList<Message_In>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_In _item;
        _item = new Message_In();
        final String _tmpMessageDateOfCreated;
        _tmpMessageDateOfCreated = _cursor.getString(_cursorIndexOfMessageDateOfCreated);
        _item.setMessageDateOfCreated(_tmpMessageDateOfCreated);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
        final String _tmpMessages;
        _tmpMessages = _cursor.getString(_cursorIndexOfMessages);
        _item.setMessages(_tmpMessages);
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

  @Override
  public List<Message_In> getInboxMessagesStudentThread(String messagein_id) {
    final String _sql = "SELECT * FROM Message_In where thread_id= ? or messagein_id= ? order by message_dateCreated asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (messagein_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, messagein_id);
    }
    _argIndex = 2;
    if (messagein_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, messagein_id);
    }
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

  @Override
  public List<Message_In> getOutMessagesStudent(String student_id) {
    final String _sql = "SELECT message_dateCreated,message_sub,thread_id,messagein_id ,messages,message_sender,message_receiver FROM Message_In where message_sender= ? order by message_dateCreated desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (student_id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, student_id);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageDateOfCreated = _cursor.getColumnIndexOrThrow("message_dateCreated");
      final int _cursorIndexOfMessageSub = _cursor.getColumnIndexOrThrow("message_sub");
      final int _cursorIndexOfThreadId = _cursor.getColumnIndexOrThrow("thread_id");
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messagein_id");
      final int _cursorIndexOfMessages = _cursor.getColumnIndexOrThrow("messages");
      final int _cursorIndexOfMessageSender = _cursor.getColumnIndexOrThrow("message_sender");
      final int _cursorIndexOfMessageReceiver = _cursor.getColumnIndexOrThrow("message_receiver");
      final List<Message_In> _result = new ArrayList<Message_In>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_In _item;
        _item = new Message_In();
        final String _tmpMessageDateOfCreated;
        _tmpMessageDateOfCreated = _cursor.getString(_cursorIndexOfMessageDateOfCreated);
        _item.setMessageDateOfCreated(_tmpMessageDateOfCreated);
        final String _tmpMessageSub;
        _tmpMessageSub = _cursor.getString(_cursorIndexOfMessageSub);
        _item.setMessageSub(_tmpMessageSub);
        final String _tmpThreadId;
        _tmpThreadId = _cursor.getString(_cursorIndexOfThreadId);
        _item.setThreadId(_tmpThreadId);
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
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
  public int getMessageCount() {
    final String _sql = "select count(messagein_id) from Message_In";
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
    final String _sql = "select student_id from Message_In where 'rowid' = (select max('rowid') from Message_In)";
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
  public List<Message_In> getlastStudentIdMessageIds() {
    final String _sql = "select messagein_id,student_id,message_dateCreated from Message_In group by student_id  order by message_dateCreated desc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMessageInId = _cursor.getColumnIndexOrThrow("messagein_id");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfMessageDateOfCreated = _cursor.getColumnIndexOrThrow("message_dateCreated");
      final List<Message_In> _result = new ArrayList<Message_In>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Message_In _item;
        _item = new Message_In();
        final String _tmpMessageInId;
        _tmpMessageInId = _cursor.getString(_cursorIndexOfMessageInId);
        _item.setMessageInId(_tmpMessageInId);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final String _tmpMessageDateOfCreated;
        _tmpMessageDateOfCreated = _cursor.getString(_cursorIndexOfMessageDateOfCreated);
        _item.setMessageDateOfCreated(_tmpMessageDateOfCreated);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
