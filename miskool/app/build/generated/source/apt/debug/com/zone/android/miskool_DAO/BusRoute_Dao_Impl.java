package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.Bus_Route;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class BusRoute_Dao_Impl implements BusRoute_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfBus_Route;

  public BusRoute_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBus_Route = new EntityInsertionAdapter<Bus_Route>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Bus_Route`(`route_id`,`route_name`,`bus_id`,`driver_name`,`driver_phone`,`helper_name`,`helper_phone`,`student_id`,`stop_no`,`stop_name`,`stop_lat`,`stop_lon`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Bus_Route value) {
        if (value.getRouteId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRouteId());
        }
        if (value.getRouteName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRouteName());
        }
        if (value.getBusId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getBusId());
        }
        if (value.getDriverName() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDriverName());
        }
        if (value.getDriverPhone() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getDriverPhone());
        }
        if (value.getHelperName() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getHelperName());
        }
        if (value.getHelperPhone() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getHelperPhone());
        }
        if (value.getStudentId() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getStudentId());
        }
        stmt.bindLong(9, value.getStopNo());
        if (value.getStopName() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getStopName());
        }
        if (value.getStopLat() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getStopLat());
        }
        if (value.getStopLon() == null) {
          stmt.bindNull(12);
        } else {
          stmt.bindString(12, value.getStopLon());
        }
      }
    };
  }

  @Override
  public void insertAll(Bus_Route... routes) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfBus_Route.insert(routes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Bus_Route> getAll() {
    final String _sql = "SELECT * FROM Bus_Route";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRouteId = _cursor.getColumnIndexOrThrow("route_id");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("route_name");
      final int _cursorIndexOfBusId = _cursor.getColumnIndexOrThrow("bus_id");
      final int _cursorIndexOfDriverName = _cursor.getColumnIndexOrThrow("driver_name");
      final int _cursorIndexOfDriverPhone = _cursor.getColumnIndexOrThrow("driver_phone");
      final int _cursorIndexOfHelperName = _cursor.getColumnIndexOrThrow("helper_name");
      final int _cursorIndexOfHelperPhone = _cursor.getColumnIndexOrThrow("helper_phone");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStopNo = _cursor.getColumnIndexOrThrow("stop_no");
      final int _cursorIndexOfStopName = _cursor.getColumnIndexOrThrow("stop_name");
      final int _cursorIndexOfStopLat = _cursor.getColumnIndexOrThrow("stop_lat");
      final int _cursorIndexOfStopLon = _cursor.getColumnIndexOrThrow("stop_lon");
      final List<Bus_Route> _result = new ArrayList<Bus_Route>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Bus_Route _item;
        _item = new Bus_Route();
        final String _tmpRouteId;
        _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
        _item.setRouteId(_tmpRouteId);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        _item.setRouteName(_tmpRouteName);
        final String _tmpBusId;
        _tmpBusId = _cursor.getString(_cursorIndexOfBusId);
        _item.setBusId(_tmpBusId);
        final String _tmpDriverName;
        _tmpDriverName = _cursor.getString(_cursorIndexOfDriverName);
        _item.setDriverName(_tmpDriverName);
        final String _tmpDriverPhone;
        _tmpDriverPhone = _cursor.getString(_cursorIndexOfDriverPhone);
        _item.setDriverPhone(_tmpDriverPhone);
        final String _tmpHelperName;
        _tmpHelperName = _cursor.getString(_cursorIndexOfHelperName);
        _item.setHelperName(_tmpHelperName);
        final String _tmpHelperPhone;
        _tmpHelperPhone = _cursor.getString(_cursorIndexOfHelperPhone);
        _item.setHelperPhone(_tmpHelperPhone);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final int _tmpStopNo;
        _tmpStopNo = _cursor.getInt(_cursorIndexOfStopNo);
        _item.setStopNo(_tmpStopNo);
        final String _tmpStopName;
        _tmpStopName = _cursor.getString(_cursorIndexOfStopName);
        _item.setStopName(_tmpStopName);
        final String _tmpStopLat;
        _tmpStopLat = _cursor.getString(_cursorIndexOfStopLat);
        _item.setStopLat(_tmpStopLat);
        final String _tmpStopLon;
        _tmpStopLon = _cursor.getString(_cursorIndexOfStopLon);
        _item.setStopLon(_tmpStopLon);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Bus_Route> getAllRoutes() {
    final String _sql = "SELECT * FROM Bus_Route";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRouteId = _cursor.getColumnIndexOrThrow("route_id");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("route_name");
      final int _cursorIndexOfBusId = _cursor.getColumnIndexOrThrow("bus_id");
      final int _cursorIndexOfDriverName = _cursor.getColumnIndexOrThrow("driver_name");
      final int _cursorIndexOfDriverPhone = _cursor.getColumnIndexOrThrow("driver_phone");
      final int _cursorIndexOfHelperName = _cursor.getColumnIndexOrThrow("helper_name");
      final int _cursorIndexOfHelperPhone = _cursor.getColumnIndexOrThrow("helper_phone");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStopNo = _cursor.getColumnIndexOrThrow("stop_no");
      final int _cursorIndexOfStopName = _cursor.getColumnIndexOrThrow("stop_name");
      final int _cursorIndexOfStopLat = _cursor.getColumnIndexOrThrow("stop_lat");
      final int _cursorIndexOfStopLon = _cursor.getColumnIndexOrThrow("stop_lon");
      final List<Bus_Route> _result = new ArrayList<Bus_Route>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Bus_Route _item;
        _item = new Bus_Route();
        final String _tmpRouteId;
        _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
        _item.setRouteId(_tmpRouteId);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        _item.setRouteName(_tmpRouteName);
        final String _tmpBusId;
        _tmpBusId = _cursor.getString(_cursorIndexOfBusId);
        _item.setBusId(_tmpBusId);
        final String _tmpDriverName;
        _tmpDriverName = _cursor.getString(_cursorIndexOfDriverName);
        _item.setDriverName(_tmpDriverName);
        final String _tmpDriverPhone;
        _tmpDriverPhone = _cursor.getString(_cursorIndexOfDriverPhone);
        _item.setDriverPhone(_tmpDriverPhone);
        final String _tmpHelperName;
        _tmpHelperName = _cursor.getString(_cursorIndexOfHelperName);
        _item.setHelperName(_tmpHelperName);
        final String _tmpHelperPhone;
        _tmpHelperPhone = _cursor.getString(_cursorIndexOfHelperPhone);
        _item.setHelperPhone(_tmpHelperPhone);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final int _tmpStopNo;
        _tmpStopNo = _cursor.getInt(_cursorIndexOfStopNo);
        _item.setStopNo(_tmpStopNo);
        final String _tmpStopName;
        _tmpStopName = _cursor.getString(_cursorIndexOfStopName);
        _item.setStopName(_tmpStopName);
        final String _tmpStopLat;
        _tmpStopLat = _cursor.getString(_cursorIndexOfStopLat);
        _item.setStopLat(_tmpStopLat);
        final String _tmpStopLon;
        _tmpStopLon = _cursor.getString(_cursorIndexOfStopLon);
        _item.setStopLon(_tmpStopLon);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Bus_Route> getRoutesOfStudent(String studentId) {
    final String _sql = "SELECT * FROM Bus_Route where student_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (studentId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, studentId);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRouteId = _cursor.getColumnIndexOrThrow("route_id");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("route_name");
      final int _cursorIndexOfBusId = _cursor.getColumnIndexOrThrow("bus_id");
      final int _cursorIndexOfDriverName = _cursor.getColumnIndexOrThrow("driver_name");
      final int _cursorIndexOfDriverPhone = _cursor.getColumnIndexOrThrow("driver_phone");
      final int _cursorIndexOfHelperName = _cursor.getColumnIndexOrThrow("helper_name");
      final int _cursorIndexOfHelperPhone = _cursor.getColumnIndexOrThrow("helper_phone");
      final int _cursorIndexOfStudentId = _cursor.getColumnIndexOrThrow("student_id");
      final int _cursorIndexOfStopNo = _cursor.getColumnIndexOrThrow("stop_no");
      final int _cursorIndexOfStopName = _cursor.getColumnIndexOrThrow("stop_name");
      final int _cursorIndexOfStopLat = _cursor.getColumnIndexOrThrow("stop_lat");
      final int _cursorIndexOfStopLon = _cursor.getColumnIndexOrThrow("stop_lon");
      final List<Bus_Route> _result = new ArrayList<Bus_Route>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Bus_Route _item;
        _item = new Bus_Route();
        final String _tmpRouteId;
        _tmpRouteId = _cursor.getString(_cursorIndexOfRouteId);
        _item.setRouteId(_tmpRouteId);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        _item.setRouteName(_tmpRouteName);
        final String _tmpBusId;
        _tmpBusId = _cursor.getString(_cursorIndexOfBusId);
        _item.setBusId(_tmpBusId);
        final String _tmpDriverName;
        _tmpDriverName = _cursor.getString(_cursorIndexOfDriverName);
        _item.setDriverName(_tmpDriverName);
        final String _tmpDriverPhone;
        _tmpDriverPhone = _cursor.getString(_cursorIndexOfDriverPhone);
        _item.setDriverPhone(_tmpDriverPhone);
        final String _tmpHelperName;
        _tmpHelperName = _cursor.getString(_cursorIndexOfHelperName);
        _item.setHelperName(_tmpHelperName);
        final String _tmpHelperPhone;
        _tmpHelperPhone = _cursor.getString(_cursorIndexOfHelperPhone);
        _item.setHelperPhone(_tmpHelperPhone);
        final String _tmpStudentId;
        _tmpStudentId = _cursor.getString(_cursorIndexOfStudentId);
        _item.setStudentId(_tmpStudentId);
        final int _tmpStopNo;
        _tmpStopNo = _cursor.getInt(_cursorIndexOfStopNo);
        _item.setStopNo(_tmpStopNo);
        final String _tmpStopName;
        _tmpStopName = _cursor.getString(_cursorIndexOfStopName);
        _item.setStopName(_tmpStopName);
        final String _tmpStopLat;
        _tmpStopLat = _cursor.getString(_cursorIndexOfStopLat);
        _item.setStopLat(_tmpStopLat);
        final String _tmpStopLon;
        _tmpStopLon = _cursor.getString(_cursorIndexOfStopLon);
        _item.setStopLon(_tmpStopLon);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
