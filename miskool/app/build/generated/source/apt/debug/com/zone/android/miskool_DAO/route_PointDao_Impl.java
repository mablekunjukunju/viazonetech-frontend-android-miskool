package com.zone.android.miskool_DAO;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import com.zone.android.miskool_Entitiy.RoutePoint;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class route_PointDao_Impl implements route_PointDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfRoutePoint;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoute;

  public route_PointDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoutePoint = new EntityInsertionAdapter<RoutePoint>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `RoutePoint`(`route_key`,`route_name`,`point_id`,`route_index`,`route_lat`,`route_long`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, RoutePoint value) {
        if (value.getRouteKey() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getRouteKey());
        }
        if (value.getRouteName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getRouteName());
        }
        if (value.getPointId() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPointId());
        }
        stmt.bindLong(4, value.getRouteIndex());
        if (value.getRouteLat() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getRouteLat());
        }
        if (value.getRouteLong() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getRouteLong());
        }
      }
    };
    this.__preparedStmtOfDeleteRoute = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM RoutePoint";
        return _query;
      }
    };
  }

  @Override
  public void insertAll(RoutePoint... routePoints) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfRoutePoint.insert(routePoints);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void DeleteRoute() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoute.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteRoute.release(_stmt);
    }
  }

  @Override
  public List<RoutePoint> getAll() {
    final String _sql = "SELECT * FROM RoutePoint order by route_index asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRouteKey = _cursor.getColumnIndexOrThrow("route_key");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("route_name");
      final int _cursorIndexOfPointId = _cursor.getColumnIndexOrThrow("point_id");
      final int _cursorIndexOfRouteIndex = _cursor.getColumnIndexOrThrow("route_index");
      final int _cursorIndexOfRouteLat = _cursor.getColumnIndexOrThrow("route_lat");
      final int _cursorIndexOfRouteLong = _cursor.getColumnIndexOrThrow("route_long");
      final List<RoutePoint> _result = new ArrayList<RoutePoint>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RoutePoint _item;
        _item = new RoutePoint();
        final String _tmpRouteKey;
        _tmpRouteKey = _cursor.getString(_cursorIndexOfRouteKey);
        _item.setRouteKey(_tmpRouteKey);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        _item.setRouteName(_tmpRouteName);
        final String _tmpPointId;
        _tmpPointId = _cursor.getString(_cursorIndexOfPointId);
        _item.setPointId(_tmpPointId);
        final int _tmpRouteIndex;
        _tmpRouteIndex = _cursor.getInt(_cursorIndexOfRouteIndex);
        _item.setRouteIndex(_tmpRouteIndex);
        final String _tmpRouteLat;
        _tmpRouteLat = _cursor.getString(_cursorIndexOfRouteLat);
        _item.setRouteLat(_tmpRouteLat);
        final String _tmpRouteLong;
        _tmpRouteLong = _cursor.getString(_cursorIndexOfRouteLong);
        _item.setRouteLong(_tmpRouteLong);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<RoutePoint> getIndexVals(int index) {
    final String _sql = "SELECT * FROM RoutePoint where route_index= ? or route_index= ?+1 order by route_index asc";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, index);
    _argIndex = 2;
    _statement.bindLong(_argIndex, index);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfRouteKey = _cursor.getColumnIndexOrThrow("route_key");
      final int _cursorIndexOfRouteName = _cursor.getColumnIndexOrThrow("route_name");
      final int _cursorIndexOfPointId = _cursor.getColumnIndexOrThrow("point_id");
      final int _cursorIndexOfRouteIndex = _cursor.getColumnIndexOrThrow("route_index");
      final int _cursorIndexOfRouteLat = _cursor.getColumnIndexOrThrow("route_lat");
      final int _cursorIndexOfRouteLong = _cursor.getColumnIndexOrThrow("route_long");
      final List<RoutePoint> _result = new ArrayList<RoutePoint>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final RoutePoint _item;
        _item = new RoutePoint();
        final String _tmpRouteKey;
        _tmpRouteKey = _cursor.getString(_cursorIndexOfRouteKey);
        _item.setRouteKey(_tmpRouteKey);
        final String _tmpRouteName;
        _tmpRouteName = _cursor.getString(_cursorIndexOfRouteName);
        _item.setRouteName(_tmpRouteName);
        final String _tmpPointId;
        _tmpPointId = _cursor.getString(_cursorIndexOfPointId);
        _item.setPointId(_tmpPointId);
        final int _tmpRouteIndex;
        _tmpRouteIndex = _cursor.getInt(_cursorIndexOfRouteIndex);
        _item.setRouteIndex(_tmpRouteIndex);
        final String _tmpRouteLat;
        _tmpRouteLat = _cursor.getString(_cursorIndexOfRouteLat);
        _item.setRouteLat(_tmpRouteLat);
        final String _tmpRouteLong;
        _tmpRouteLong = _cursor.getString(_cursorIndexOfRouteLong);
        _item.setRouteLong(_tmpRouteLong);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
