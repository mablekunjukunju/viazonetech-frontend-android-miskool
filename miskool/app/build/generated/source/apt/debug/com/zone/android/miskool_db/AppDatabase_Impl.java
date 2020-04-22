package com.zone.android.miskool_db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import com.zone.android.miskool_DAO.BusRoute_Dao;
import com.zone.android.miskool_DAO.BusRoute_Dao_Impl;
import com.zone.android.miskool_DAO.Message_InMessage_det_Dao;
import com.zone.android.miskool_DAO.Message_InMessage_det_Dao_Impl;
import com.zone.android.miskool_DAO.account_Dao;
import com.zone.android.miskool_DAO.account_Dao_Impl;
import com.zone.android.miskool_DAO.alert_Dao;
import com.zone.android.miskool_DAO.alert_Dao_Impl;
import com.zone.android.miskool_DAO.attribute_Dao;
import com.zone.android.miskool_DAO.attribute_Dao_Impl;
import com.zone.android.miskool_DAO.background_Dao;
import com.zone.android.miskool_DAO.background_Dao_Impl;
import com.zone.android.miskool_DAO.config_Dao;
import com.zone.android.miskool_DAO.config_Dao_Impl;
import com.zone.android.miskool_DAO.contacts_Dao;
import com.zone.android.miskool_DAO.contacts_Dao_Impl;
import com.zone.android.miskool_DAO.messageIn_Dao;
import com.zone.android.miskool_DAO.messageIn_Dao_Impl;
import com.zone.android.miskool_DAO.messageOut_Dao;
import com.zone.android.miskool_DAO.messageOut_Dao_Impl;
import com.zone.android.miskool_DAO.message_Dao;
import com.zone.android.miskool_DAO.message_Dao_Impl;
import com.zone.android.miskool_DAO.messagedet_Dao;
import com.zone.android.miskool_DAO.messagedet_Dao_Impl;
import com.zone.android.miskool_DAO.person_Dao;
import com.zone.android.miskool_DAO.person_Dao_Impl;
import com.zone.android.miskool_DAO.route_PointDao;
import com.zone.android.miskool_DAO.route_PointDao_Impl;
import com.zone.android.miskool_DAO.schedule_Dao;
import com.zone.android.miskool_DAO.schedule_Dao_Impl;
import com.zone.android.miskool_DAO.speech_DAO;
import com.zone.android.miskool_DAO.speech_DAO_Impl;
import com.zone.android.miskool_DAO.timetable_Dao;
import com.zone.android.miskool_DAO.timetable_Dao_Impl;
import com.zone.android.miskool_DAO.token_Dao;
import com.zone.android.miskool_DAO.token_Dao_Impl;
import com.zone.android.miskool_DAO.user_Dao;
import com.zone.android.miskool_DAO.user_Dao_Impl;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile messageIn_Dao _messageInDao;

  private volatile messagedet_Dao _messagedetDao;

  private volatile Message_InMessage_det_Dao _messageInMessageDetDao;

  private volatile user_Dao _userDao;

  private volatile config_Dao _configDao;

  private volatile token_Dao _tokenDao;

  private volatile BusRoute_Dao _busRouteDao;

  private volatile person_Dao _personDao;

  private volatile alert_Dao _alertDao;

  private volatile message_Dao _messageDao;

  private volatile messageOut_Dao _messageOutDao;

  private volatile account_Dao _accountDao;

  private volatile attribute_Dao _attributeDao;

  private volatile background_Dao _backgroundDao;

  private volatile timetable_Dao _timetableDao;

  private volatile schedule_Dao _scheduleDao;

  private volatile route_PointDao _routePointDao;

  private volatile speech_DAO _speechDAO;

  private volatile contacts_Dao _contactsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `User` (`uid` INTEGER NOT NULL, `user_name` TEXT, PRIMARY KEY(`uid`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message_In` (`messagein_id` TEXT NOT NULL, `thread_id` TEXT, `student_id` TEXT, `message_dateOfArrival` TEXT, `message_sub` TEXT, `messages` TEXT, `message_type` TEXT, `message_dateCreated` TEXT, `message_sender` TEXT, `message_receiver` TEXT, `read_flag` TEXT, PRIMARY KEY(`messagein_id`))");
        _db.execSQL("CREATE  INDEX `index_Message_In_messagein_id` ON `Message_In` (`messagein_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `timetable` (`date_id` TEXT NOT NULL, `time` TEXT, `sub` TEXT, PRIMARY KEY(`date_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message_det` (`instance_id` TEXT NOT NULL, `msg_id` TEXT, `messages` TEXT, `message_type` TEXT, `message_timeRecent` TEXT, `message_sender` TEXT, `message_receiver` TEXT, PRIMARY KEY(`instance_id`), FOREIGN KEY(`msg_id`) REFERENCES `Message_In`(`messagein_id`) ON UPDATE NO ACTION ON DELETE NO ACTION )");
        _db.execSQL("CREATE  INDEX `index_Message_det_instance_id` ON `Message_det` (`instance_id`)");
        _db.execSQL("CREATE  INDEX `index_Message_det_msg_id` ON `Message_det` (`msg_id`)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Config_det` (`service_id` TEXT NOT NULL, `service_name` TEXT, `service_url` TEXT, PRIMARY KEY(`service_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Token_det` (`token_id` INTEGER NOT NULL, `token` TEXT, `token_inc` INTEGER NOT NULL, PRIMARY KEY(`token_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Bus_Route` (`route_id` TEXT NOT NULL, `route_name` TEXT, `bus_id` TEXT, `driver_name` TEXT, `driver_phone` TEXT, `helper_name` TEXT, `helper_phone` TEXT, `student_id` TEXT, `stop_no` INTEGER NOT NULL, `stop_name` TEXT, `stop_lat` TEXT, `stop_lon` TEXT, PRIMARY KEY(`route_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Attributes` (`rowwwid` TEXT NOT NULL, `studentId` TEXT, `attName` TEXT, `sttValue` TEXT, `studentName` TEXT, PRIMARY KEY(`rowwwid`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Person_det` (`student_id` TEXT NOT NULL, `first_name` TEXT, `identitiy` TEXT, `last_name` TEXT, PRIMARY KEY(`student_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Alerts` (`alert_id` TEXT NOT NULL, `alert_date` TEXT, `alert_priority` TEXT, `alert_enddate` TEXT, `alert_sub` TEXT, `alert_message` TEXT, `student_id` TEXT, `active` TEXT, PRIMARY KEY(`alert_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message` (`messageIn_id` TEXT NOT NULL, `thread_id` TEXT, `student_id` TEXT, `message_created` TEXT, `message_type` TEXT, `messages` TEXT, `message_sub` TEXT, `message_timeRecent` TEXT, `message_sender` TEXT, `message_receiver` TEXT, PRIMARY KEY(`messageIn_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Message_Out` (`out_id` TEXT NOT NULL, `student_id` TEXT, `message_date` TEXT, `message_sub` TEXT, `message_content` TEXT, `message_receiver` TEXT, PRIMARY KEY(`out_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Account` (`person_id` TEXT NOT NULL, `person_name` TEXT, PRIMARY KEY(`person_id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `backGroundDB` (`json_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `json_name` TEXT, `json_value` TEXT, `update_status` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Schedule` (`schedule_id` TEXT, `student_id` TEXT, `starttime_null` TEXT, `endtime_null` TEXT, `pkey` TEXT NOT NULL, `starttime` TEXT, `endtime` TEXT, `active` TEXT, `subject` TEXT, `text` TEXT, PRIMARY KEY(`pkey`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `RoutePoint` (`route_key` TEXT NOT NULL, `route_name` TEXT, `point_id` TEXT, `route_index` INTEGER NOT NULL, `route_lat` TEXT, `route_long` TEXT, PRIMARY KEY(`route_key`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Speech` (`speech_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `thread_id` TEXT, `student_id` TEXT, `message_date` TEXT, `messages` TEXT, `message_sender` TEXT, `message_receiver` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Contacts` (`pkey` TEXT NOT NULL, `username` TEXT, `combName` TEXT, `lastname` TEXT, `firstname` TEXT, `othernames` TEXT, PRIMARY KEY(`pkey`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e4fe58f8a9e39ccc562700656a7a5ef5\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `User`");
        _db.execSQL("DROP TABLE IF EXISTS `Message_In`");
        _db.execSQL("DROP TABLE IF EXISTS `timetable`");
        _db.execSQL("DROP TABLE IF EXISTS `Message_det`");
        _db.execSQL("DROP TABLE IF EXISTS `Config_det`");
        _db.execSQL("DROP TABLE IF EXISTS `Token_det`");
        _db.execSQL("DROP TABLE IF EXISTS `Bus_Route`");
        _db.execSQL("DROP TABLE IF EXISTS `Attributes`");
        _db.execSQL("DROP TABLE IF EXISTS `Person_det`");
        _db.execSQL("DROP TABLE IF EXISTS `Alerts`");
        _db.execSQL("DROP TABLE IF EXISTS `Message`");
        _db.execSQL("DROP TABLE IF EXISTS `Message_Out`");
        _db.execSQL("DROP TABLE IF EXISTS `Account`");
        _db.execSQL("DROP TABLE IF EXISTS `backGroundDB`");
        _db.execSQL("DROP TABLE IF EXISTS `Schedule`");
        _db.execSQL("DROP TABLE IF EXISTS `RoutePoint`");
        _db.execSQL("DROP TABLE IF EXISTS `Speech`");
        _db.execSQL("DROP TABLE IF EXISTS `Contacts`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        _db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsUser = new HashMap<String, TableInfo.Column>(2);
        _columnsUser.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1));
        _columnsUser.put("user_name", new TableInfo.Column("user_name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUser = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUser = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUser = new TableInfo("User", _columnsUser, _foreignKeysUser, _indicesUser);
        final TableInfo _existingUser = TableInfo.read(_db, "User");
        if (! _infoUser.equals(_existingUser)) {
          throw new IllegalStateException("Migration didn't properly handle User(com.zone.android.miskool_Entitiy.User).\n"
                  + " Expected:\n" + _infoUser + "\n"
                  + " Found:\n" + _existingUser);
        }
        final HashMap<String, TableInfo.Column> _columnsMessageIn = new HashMap<String, TableInfo.Column>(11);
        _columnsMessageIn.put("messagein_id", new TableInfo.Column("messagein_id", "TEXT", true, 1));
        _columnsMessageIn.put("thread_id", new TableInfo.Column("thread_id", "TEXT", false, 0));
        _columnsMessageIn.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsMessageIn.put("message_dateOfArrival", new TableInfo.Column("message_dateOfArrival", "TEXT", false, 0));
        _columnsMessageIn.put("message_sub", new TableInfo.Column("message_sub", "TEXT", false, 0));
        _columnsMessageIn.put("messages", new TableInfo.Column("messages", "TEXT", false, 0));
        _columnsMessageIn.put("message_type", new TableInfo.Column("message_type", "TEXT", false, 0));
        _columnsMessageIn.put("message_dateCreated", new TableInfo.Column("message_dateCreated", "TEXT", false, 0));
        _columnsMessageIn.put("message_sender", new TableInfo.Column("message_sender", "TEXT", false, 0));
        _columnsMessageIn.put("message_receiver", new TableInfo.Column("message_receiver", "TEXT", false, 0));
        _columnsMessageIn.put("read_flag", new TableInfo.Column("read_flag", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessageIn = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessageIn = new HashSet<TableInfo.Index>(1);
        _indicesMessageIn.add(new TableInfo.Index("index_Message_In_messagein_id", false, Arrays.asList("messagein_id")));
        final TableInfo _infoMessageIn = new TableInfo("Message_In", _columnsMessageIn, _foreignKeysMessageIn, _indicesMessageIn);
        final TableInfo _existingMessageIn = TableInfo.read(_db, "Message_In");
        if (! _infoMessageIn.equals(_existingMessageIn)) {
          throw new IllegalStateException("Migration didn't properly handle Message_In(com.zone.android.miskool_Entitiy.Message_In).\n"
                  + " Expected:\n" + _infoMessageIn + "\n"
                  + " Found:\n" + _existingMessageIn);
        }
        final HashMap<String, TableInfo.Column> _columnsTimetable = new HashMap<String, TableInfo.Column>(3);
        _columnsTimetable.put("date_id", new TableInfo.Column("date_id", "TEXT", true, 1));
        _columnsTimetable.put("time", new TableInfo.Column("time", "TEXT", false, 0));
        _columnsTimetable.put("sub", new TableInfo.Column("sub", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTimetable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTimetable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTimetable = new TableInfo("timetable", _columnsTimetable, _foreignKeysTimetable, _indicesTimetable);
        final TableInfo _existingTimetable = TableInfo.read(_db, "timetable");
        if (! _infoTimetable.equals(_existingTimetable)) {
          throw new IllegalStateException("Migration didn't properly handle timetable(com.zone.android.miskool_Entitiy.timetable).\n"
                  + " Expected:\n" + _infoTimetable + "\n"
                  + " Found:\n" + _existingTimetable);
        }
        final HashMap<String, TableInfo.Column> _columnsMessageDet = new HashMap<String, TableInfo.Column>(7);
        _columnsMessageDet.put("instance_id", new TableInfo.Column("instance_id", "TEXT", true, 1));
        _columnsMessageDet.put("msg_id", new TableInfo.Column("msg_id", "TEXT", false, 0));
        _columnsMessageDet.put("messages", new TableInfo.Column("messages", "TEXT", false, 0));
        _columnsMessageDet.put("message_type", new TableInfo.Column("message_type", "TEXT", false, 0));
        _columnsMessageDet.put("message_timeRecent", new TableInfo.Column("message_timeRecent", "TEXT", false, 0));
        _columnsMessageDet.put("message_sender", new TableInfo.Column("message_sender", "TEXT", false, 0));
        _columnsMessageDet.put("message_receiver", new TableInfo.Column("message_receiver", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessageDet = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysMessageDet.add(new TableInfo.ForeignKey("Message_In", "NO ACTION", "NO ACTION",Arrays.asList("msg_id"), Arrays.asList("messagein_id")));
        final HashSet<TableInfo.Index> _indicesMessageDet = new HashSet<TableInfo.Index>(2);
        _indicesMessageDet.add(new TableInfo.Index("index_Message_det_instance_id", false, Arrays.asList("instance_id")));
        _indicesMessageDet.add(new TableInfo.Index("index_Message_det_msg_id", false, Arrays.asList("msg_id")));
        final TableInfo _infoMessageDet = new TableInfo("Message_det", _columnsMessageDet, _foreignKeysMessageDet, _indicesMessageDet);
        final TableInfo _existingMessageDet = TableInfo.read(_db, "Message_det");
        if (! _infoMessageDet.equals(_existingMessageDet)) {
          throw new IllegalStateException("Migration didn't properly handle Message_det(com.zone.android.miskool_Entitiy.Message_det).\n"
                  + " Expected:\n" + _infoMessageDet + "\n"
                  + " Found:\n" + _existingMessageDet);
        }
        final HashMap<String, TableInfo.Column> _columnsConfigDet = new HashMap<String, TableInfo.Column>(3);
        _columnsConfigDet.put("service_id", new TableInfo.Column("service_id", "TEXT", true, 1));
        _columnsConfigDet.put("service_name", new TableInfo.Column("service_name", "TEXT", false, 0));
        _columnsConfigDet.put("service_url", new TableInfo.Column("service_url", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConfigDet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConfigDet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConfigDet = new TableInfo("Config_det", _columnsConfigDet, _foreignKeysConfigDet, _indicesConfigDet);
        final TableInfo _existingConfigDet = TableInfo.read(_db, "Config_det");
        if (! _infoConfigDet.equals(_existingConfigDet)) {
          throw new IllegalStateException("Migration didn't properly handle Config_det(com.zone.android.miskool_Entitiy.Config_det).\n"
                  + " Expected:\n" + _infoConfigDet + "\n"
                  + " Found:\n" + _existingConfigDet);
        }
        final HashMap<String, TableInfo.Column> _columnsTokenDet = new HashMap<String, TableInfo.Column>(3);
        _columnsTokenDet.put("token_id", new TableInfo.Column("token_id", "INTEGER", true, 1));
        _columnsTokenDet.put("token", new TableInfo.Column("token", "TEXT", false, 0));
        _columnsTokenDet.put("token_inc", new TableInfo.Column("token_inc", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTokenDet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTokenDet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTokenDet = new TableInfo("Token_det", _columnsTokenDet, _foreignKeysTokenDet, _indicesTokenDet);
        final TableInfo _existingTokenDet = TableInfo.read(_db, "Token_det");
        if (! _infoTokenDet.equals(_existingTokenDet)) {
          throw new IllegalStateException("Migration didn't properly handle Token_det(com.zone.android.miskool_Entitiy.Token_det).\n"
                  + " Expected:\n" + _infoTokenDet + "\n"
                  + " Found:\n" + _existingTokenDet);
        }
        final HashMap<String, TableInfo.Column> _columnsBusRoute = new HashMap<String, TableInfo.Column>(12);
        _columnsBusRoute.put("route_id", new TableInfo.Column("route_id", "TEXT", true, 1));
        _columnsBusRoute.put("route_name", new TableInfo.Column("route_name", "TEXT", false, 0));
        _columnsBusRoute.put("bus_id", new TableInfo.Column("bus_id", "TEXT", false, 0));
        _columnsBusRoute.put("driver_name", new TableInfo.Column("driver_name", "TEXT", false, 0));
        _columnsBusRoute.put("driver_phone", new TableInfo.Column("driver_phone", "TEXT", false, 0));
        _columnsBusRoute.put("helper_name", new TableInfo.Column("helper_name", "TEXT", false, 0));
        _columnsBusRoute.put("helper_phone", new TableInfo.Column("helper_phone", "TEXT", false, 0));
        _columnsBusRoute.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsBusRoute.put("stop_no", new TableInfo.Column("stop_no", "INTEGER", true, 0));
        _columnsBusRoute.put("stop_name", new TableInfo.Column("stop_name", "TEXT", false, 0));
        _columnsBusRoute.put("stop_lat", new TableInfo.Column("stop_lat", "TEXT", false, 0));
        _columnsBusRoute.put("stop_lon", new TableInfo.Column("stop_lon", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBusRoute = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBusRoute = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBusRoute = new TableInfo("Bus_Route", _columnsBusRoute, _foreignKeysBusRoute, _indicesBusRoute);
        final TableInfo _existingBusRoute = TableInfo.read(_db, "Bus_Route");
        if (! _infoBusRoute.equals(_existingBusRoute)) {
          throw new IllegalStateException("Migration didn't properly handle Bus_Route(com.zone.android.miskool_Entitiy.Bus_Route).\n"
                  + " Expected:\n" + _infoBusRoute + "\n"
                  + " Found:\n" + _existingBusRoute);
        }
        final HashMap<String, TableInfo.Column> _columnsAttributes = new HashMap<String, TableInfo.Column>(5);
        _columnsAttributes.put("rowwwid", new TableInfo.Column("rowwwid", "TEXT", true, 1));
        _columnsAttributes.put("studentId", new TableInfo.Column("studentId", "TEXT", false, 0));
        _columnsAttributes.put("attName", new TableInfo.Column("attName", "TEXT", false, 0));
        _columnsAttributes.put("sttValue", new TableInfo.Column("sttValue", "TEXT", false, 0));
        _columnsAttributes.put("studentName", new TableInfo.Column("studentName", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAttributes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAttributes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAttributes = new TableInfo("Attributes", _columnsAttributes, _foreignKeysAttributes, _indicesAttributes);
        final TableInfo _existingAttributes = TableInfo.read(_db, "Attributes");
        if (! _infoAttributes.equals(_existingAttributes)) {
          throw new IllegalStateException("Migration didn't properly handle Attributes(com.zone.android.miskool_Entitiy.Attributes).\n"
                  + " Expected:\n" + _infoAttributes + "\n"
                  + " Found:\n" + _existingAttributes);
        }
        final HashMap<String, TableInfo.Column> _columnsPersonDet = new HashMap<String, TableInfo.Column>(4);
        _columnsPersonDet.put("student_id", new TableInfo.Column("student_id", "TEXT", true, 1));
        _columnsPersonDet.put("first_name", new TableInfo.Column("first_name", "TEXT", false, 0));
        _columnsPersonDet.put("identitiy", new TableInfo.Column("identitiy", "TEXT", false, 0));
        _columnsPersonDet.put("last_name", new TableInfo.Column("last_name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPersonDet = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPersonDet = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPersonDet = new TableInfo("Person_det", _columnsPersonDet, _foreignKeysPersonDet, _indicesPersonDet);
        final TableInfo _existingPersonDet = TableInfo.read(_db, "Person_det");
        if (! _infoPersonDet.equals(_existingPersonDet)) {
          throw new IllegalStateException("Migration didn't properly handle Person_det(com.zone.android.miskool_Entitiy.Person_det).\n"
                  + " Expected:\n" + _infoPersonDet + "\n"
                  + " Found:\n" + _existingPersonDet);
        }
        final HashMap<String, TableInfo.Column> _columnsAlerts = new HashMap<String, TableInfo.Column>(8);
        _columnsAlerts.put("alert_id", new TableInfo.Column("alert_id", "TEXT", true, 1));
        _columnsAlerts.put("alert_date", new TableInfo.Column("alert_date", "TEXT", false, 0));
        _columnsAlerts.put("alert_priority", new TableInfo.Column("alert_priority", "TEXT", false, 0));
        _columnsAlerts.put("alert_enddate", new TableInfo.Column("alert_enddate", "TEXT", false, 0));
        _columnsAlerts.put("alert_sub", new TableInfo.Column("alert_sub", "TEXT", false, 0));
        _columnsAlerts.put("alert_message", new TableInfo.Column("alert_message", "TEXT", false, 0));
        _columnsAlerts.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsAlerts.put("active", new TableInfo.Column("active", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAlerts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAlerts = new TableInfo("Alerts", _columnsAlerts, _foreignKeysAlerts, _indicesAlerts);
        final TableInfo _existingAlerts = TableInfo.read(_db, "Alerts");
        if (! _infoAlerts.equals(_existingAlerts)) {
          throw new IllegalStateException("Migration didn't properly handle Alerts(com.zone.android.miskool_Entitiy.Alerts).\n"
                  + " Expected:\n" + _infoAlerts + "\n"
                  + " Found:\n" + _existingAlerts);
        }
        final HashMap<String, TableInfo.Column> _columnsMessage = new HashMap<String, TableInfo.Column>(10);
        _columnsMessage.put("messageIn_id", new TableInfo.Column("messageIn_id", "TEXT", true, 1));
        _columnsMessage.put("thread_id", new TableInfo.Column("thread_id", "TEXT", false, 0));
        _columnsMessage.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsMessage.put("message_created", new TableInfo.Column("message_created", "TEXT", false, 0));
        _columnsMessage.put("message_type", new TableInfo.Column("message_type", "TEXT", false, 0));
        _columnsMessage.put("messages", new TableInfo.Column("messages", "TEXT", false, 0));
        _columnsMessage.put("message_sub", new TableInfo.Column("message_sub", "TEXT", false, 0));
        _columnsMessage.put("message_timeRecent", new TableInfo.Column("message_timeRecent", "TEXT", false, 0));
        _columnsMessage.put("message_sender", new TableInfo.Column("message_sender", "TEXT", false, 0));
        _columnsMessage.put("message_receiver", new TableInfo.Column("message_receiver", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessage = new TableInfo("Message", _columnsMessage, _foreignKeysMessage, _indicesMessage);
        final TableInfo _existingMessage = TableInfo.read(_db, "Message");
        if (! _infoMessage.equals(_existingMessage)) {
          throw new IllegalStateException("Migration didn't properly handle Message(com.zone.android.miskool_Entitiy.Message).\n"
                  + " Expected:\n" + _infoMessage + "\n"
                  + " Found:\n" + _existingMessage);
        }
        final HashMap<String, TableInfo.Column> _columnsMessageOut = new HashMap<String, TableInfo.Column>(6);
        _columnsMessageOut.put("out_id", new TableInfo.Column("out_id", "TEXT", true, 1));
        _columnsMessageOut.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsMessageOut.put("message_date", new TableInfo.Column("message_date", "TEXT", false, 0));
        _columnsMessageOut.put("message_sub", new TableInfo.Column("message_sub", "TEXT", false, 0));
        _columnsMessageOut.put("message_content", new TableInfo.Column("message_content", "TEXT", false, 0));
        _columnsMessageOut.put("message_receiver", new TableInfo.Column("message_receiver", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMessageOut = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMessageOut = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMessageOut = new TableInfo("Message_Out", _columnsMessageOut, _foreignKeysMessageOut, _indicesMessageOut);
        final TableInfo _existingMessageOut = TableInfo.read(_db, "Message_Out");
        if (! _infoMessageOut.equals(_existingMessageOut)) {
          throw new IllegalStateException("Migration didn't properly handle Message_Out(com.zone.android.miskool_Entitiy.Message_Out).\n"
                  + " Expected:\n" + _infoMessageOut + "\n"
                  + " Found:\n" + _existingMessageOut);
        }
        final HashMap<String, TableInfo.Column> _columnsAccount = new HashMap<String, TableInfo.Column>(2);
        _columnsAccount.put("person_id", new TableInfo.Column("person_id", "TEXT", true, 1));
        _columnsAccount.put("person_name", new TableInfo.Column("person_name", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccount = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccount = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccount = new TableInfo("Account", _columnsAccount, _foreignKeysAccount, _indicesAccount);
        final TableInfo _existingAccount = TableInfo.read(_db, "Account");
        if (! _infoAccount.equals(_existingAccount)) {
          throw new IllegalStateException("Migration didn't properly handle Account(com.zone.android.miskool_Entitiy.Account).\n"
                  + " Expected:\n" + _infoAccount + "\n"
                  + " Found:\n" + _existingAccount);
        }
        final HashMap<String, TableInfo.Column> _columnsBackGroundDB = new HashMap<String, TableInfo.Column>(4);
        _columnsBackGroundDB.put("json_id", new TableInfo.Column("json_id", "INTEGER", true, 1));
        _columnsBackGroundDB.put("json_name", new TableInfo.Column("json_name", "TEXT", false, 0));
        _columnsBackGroundDB.put("json_value", new TableInfo.Column("json_value", "TEXT", false, 0));
        _columnsBackGroundDB.put("update_status", new TableInfo.Column("update_status", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBackGroundDB = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBackGroundDB = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBackGroundDB = new TableInfo("backGroundDB", _columnsBackGroundDB, _foreignKeysBackGroundDB, _indicesBackGroundDB);
        final TableInfo _existingBackGroundDB = TableInfo.read(_db, "backGroundDB");
        if (! _infoBackGroundDB.equals(_existingBackGroundDB)) {
          throw new IllegalStateException("Migration didn't properly handle backGroundDB(com.zone.android.miskool_Entitiy.backGroundDB).\n"
                  + " Expected:\n" + _infoBackGroundDB + "\n"
                  + " Found:\n" + _existingBackGroundDB);
        }
        final HashMap<String, TableInfo.Column> _columnsSchedule = new HashMap<String, TableInfo.Column>(10);
        _columnsSchedule.put("schedule_id", new TableInfo.Column("schedule_id", "TEXT", false, 0));
        _columnsSchedule.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsSchedule.put("starttime_null", new TableInfo.Column("starttime_null", "TEXT", false, 0));
        _columnsSchedule.put("endtime_null", new TableInfo.Column("endtime_null", "TEXT", false, 0));
        _columnsSchedule.put("pkey", new TableInfo.Column("pkey", "TEXT", true, 1));
        _columnsSchedule.put("starttime", new TableInfo.Column("starttime", "TEXT", false, 0));
        _columnsSchedule.put("endtime", new TableInfo.Column("endtime", "TEXT", false, 0));
        _columnsSchedule.put("active", new TableInfo.Column("active", "TEXT", false, 0));
        _columnsSchedule.put("subject", new TableInfo.Column("subject", "TEXT", false, 0));
        _columnsSchedule.put("text", new TableInfo.Column("text", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSchedule = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSchedule = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSchedule = new TableInfo("Schedule", _columnsSchedule, _foreignKeysSchedule, _indicesSchedule);
        final TableInfo _existingSchedule = TableInfo.read(_db, "Schedule");
        if (! _infoSchedule.equals(_existingSchedule)) {
          throw new IllegalStateException("Migration didn't properly handle Schedule(com.zone.android.miskool_Entitiy.Schedule).\n"
                  + " Expected:\n" + _infoSchedule + "\n"
                  + " Found:\n" + _existingSchedule);
        }
        final HashMap<String, TableInfo.Column> _columnsRoutePoint = new HashMap<String, TableInfo.Column>(6);
        _columnsRoutePoint.put("route_key", new TableInfo.Column("route_key", "TEXT", true, 1));
        _columnsRoutePoint.put("route_name", new TableInfo.Column("route_name", "TEXT", false, 0));
        _columnsRoutePoint.put("point_id", new TableInfo.Column("point_id", "TEXT", false, 0));
        _columnsRoutePoint.put("route_index", new TableInfo.Column("route_index", "INTEGER", true, 0));
        _columnsRoutePoint.put("route_lat", new TableInfo.Column("route_lat", "TEXT", false, 0));
        _columnsRoutePoint.put("route_long", new TableInfo.Column("route_long", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysRoutePoint = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesRoutePoint = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoRoutePoint = new TableInfo("RoutePoint", _columnsRoutePoint, _foreignKeysRoutePoint, _indicesRoutePoint);
        final TableInfo _existingRoutePoint = TableInfo.read(_db, "RoutePoint");
        if (! _infoRoutePoint.equals(_existingRoutePoint)) {
          throw new IllegalStateException("Migration didn't properly handle RoutePoint(com.zone.android.miskool_Entitiy.RoutePoint).\n"
                  + " Expected:\n" + _infoRoutePoint + "\n"
                  + " Found:\n" + _existingRoutePoint);
        }
        final HashMap<String, TableInfo.Column> _columnsSpeech = new HashMap<String, TableInfo.Column>(7);
        _columnsSpeech.put("speech_id", new TableInfo.Column("speech_id", "INTEGER", true, 1));
        _columnsSpeech.put("thread_id", new TableInfo.Column("thread_id", "TEXT", false, 0));
        _columnsSpeech.put("student_id", new TableInfo.Column("student_id", "TEXT", false, 0));
        _columnsSpeech.put("message_date", new TableInfo.Column("message_date", "TEXT", false, 0));
        _columnsSpeech.put("messages", new TableInfo.Column("messages", "TEXT", false, 0));
        _columnsSpeech.put("message_sender", new TableInfo.Column("message_sender", "TEXT", false, 0));
        _columnsSpeech.put("message_receiver", new TableInfo.Column("message_receiver", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSpeech = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSpeech = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSpeech = new TableInfo("Speech", _columnsSpeech, _foreignKeysSpeech, _indicesSpeech);
        final TableInfo _existingSpeech = TableInfo.read(_db, "Speech");
        if (! _infoSpeech.equals(_existingSpeech)) {
          throw new IllegalStateException("Migration didn't properly handle Speech(com.zone.android.miskool_Entitiy.Speech).\n"
                  + " Expected:\n" + _infoSpeech + "\n"
                  + " Found:\n" + _existingSpeech);
        }
        final HashMap<String, TableInfo.Column> _columnsContacts = new HashMap<String, TableInfo.Column>(6);
        _columnsContacts.put("pkey", new TableInfo.Column("pkey", "TEXT", true, 1));
        _columnsContacts.put("username", new TableInfo.Column("username", "TEXT", false, 0));
        _columnsContacts.put("combName", new TableInfo.Column("combName", "TEXT", false, 0));
        _columnsContacts.put("lastname", new TableInfo.Column("lastname", "TEXT", false, 0));
        _columnsContacts.put("firstname", new TableInfo.Column("firstname", "TEXT", false, 0));
        _columnsContacts.put("othernames", new TableInfo.Column("othernames", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContacts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContacts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContacts = new TableInfo("Contacts", _columnsContacts, _foreignKeysContacts, _indicesContacts);
        final TableInfo _existingContacts = TableInfo.read(_db, "Contacts");
        if (! _infoContacts.equals(_existingContacts)) {
          throw new IllegalStateException("Migration didn't properly handle Contacts(com.zone.android.miskool_Entitiy.Contacts).\n"
                  + " Expected:\n" + _infoContacts + "\n"
                  + " Found:\n" + _existingContacts);
        }
      }
    }, "e4fe58f8a9e39ccc562700656a7a5ef5", "c3fd04258374e621b2efe75aca04a16c");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "User","Message_In","timetable","Message_det","Config_det","Token_det","Bus_Route","Attributes","Person_det","Alerts","Message","Message_Out","Account","backGroundDB","Schedule","RoutePoint","Speech","Contacts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `User`");
      _db.execSQL("DELETE FROM `Message_In`");
      _db.execSQL("DELETE FROM `timetable`");
      _db.execSQL("DELETE FROM `Message_det`");
      _db.execSQL("DELETE FROM `Config_det`");
      _db.execSQL("DELETE FROM `Token_det`");
      _db.execSQL("DELETE FROM `Bus_Route`");
      _db.execSQL("DELETE FROM `Attributes`");
      _db.execSQL("DELETE FROM `Person_det`");
      _db.execSQL("DELETE FROM `Alerts`");
      _db.execSQL("DELETE FROM `Message`");
      _db.execSQL("DELETE FROM `Message_Out`");
      _db.execSQL("DELETE FROM `Account`");
      _db.execSQL("DELETE FROM `backGroundDB`");
      _db.execSQL("DELETE FROM `Schedule`");
      _db.execSQL("DELETE FROM `RoutePoint`");
      _db.execSQL("DELETE FROM `Speech`");
      _db.execSQL("DELETE FROM `Contacts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public messageIn_Dao messageIn_dao() {
    if (_messageInDao != null) {
      return _messageInDao;
    } else {
      synchronized(this) {
        if(_messageInDao == null) {
          _messageInDao = new messageIn_Dao_Impl(this);
        }
        return _messageInDao;
      }
    }
  }

  @Override
  public messagedet_Dao messagedet_Dao() {
    if (_messagedetDao != null) {
      return _messagedetDao;
    } else {
      synchronized(this) {
        if(_messagedetDao == null) {
          _messagedetDao = new messagedet_Dao_Impl(this);
        }
        return _messagedetDao;
      }
    }
  }

  @Override
  public Message_InMessage_det_Dao inMessage_det_dao() {
    if (_messageInMessageDetDao != null) {
      return _messageInMessageDetDao;
    } else {
      synchronized(this) {
        if(_messageInMessageDetDao == null) {
          _messageInMessageDetDao = new Message_InMessage_det_Dao_Impl(this);
        }
        return _messageInMessageDetDao;
      }
    }
  }

  @Override
  public user_Dao user_dao() {
    if (_userDao != null) {
      return _userDao;
    } else {
      synchronized(this) {
        if(_userDao == null) {
          _userDao = new user_Dao_Impl(this);
        }
        return _userDao;
      }
    }
  }

  @Override
  public config_Dao config_dao() {
    if (_configDao != null) {
      return _configDao;
    } else {
      synchronized(this) {
        if(_configDao == null) {
          _configDao = new config_Dao_Impl(this);
        }
        return _configDao;
      }
    }
  }

  @Override
  public token_Dao token_dao() {
    if (_tokenDao != null) {
      return _tokenDao;
    } else {
      synchronized(this) {
        if(_tokenDao == null) {
          _tokenDao = new token_Dao_Impl(this);
        }
        return _tokenDao;
      }
    }
  }

  @Override
  public BusRoute_Dao busRoute_dao() {
    if (_busRouteDao != null) {
      return _busRouteDao;
    } else {
      synchronized(this) {
        if(_busRouteDao == null) {
          _busRouteDao = new BusRoute_Dao_Impl(this);
        }
        return _busRouteDao;
      }
    }
  }

  @Override
  public person_Dao person_dao() {
    if (_personDao != null) {
      return _personDao;
    } else {
      synchronized(this) {
        if(_personDao == null) {
          _personDao = new person_Dao_Impl(this);
        }
        return _personDao;
      }
    }
  }

  @Override
  public alert_Dao alerts_dao() {
    if (_alertDao != null) {
      return _alertDao;
    } else {
      synchronized(this) {
        if(_alertDao == null) {
          _alertDao = new alert_Dao_Impl(this);
        }
        return _alertDao;
      }
    }
  }

  @Override
  public message_Dao message_dao() {
    if (_messageDao != null) {
      return _messageDao;
    } else {
      synchronized(this) {
        if(_messageDao == null) {
          _messageDao = new message_Dao_Impl(this);
        }
        return _messageDao;
      }
    }
  }

  @Override
  public messageOut_Dao messageOut_dao() {
    if (_messageOutDao != null) {
      return _messageOutDao;
    } else {
      synchronized(this) {
        if(_messageOutDao == null) {
          _messageOutDao = new messageOut_Dao_Impl(this);
        }
        return _messageOutDao;
      }
    }
  }

  @Override
  public account_Dao account_dao() {
    if (_accountDao != null) {
      return _accountDao;
    } else {
      synchronized(this) {
        if(_accountDao == null) {
          _accountDao = new account_Dao_Impl(this);
        }
        return _accountDao;
      }
    }
  }

  @Override
  public attribute_Dao attribute_dao() {
    if (_attributeDao != null) {
      return _attributeDao;
    } else {
      synchronized(this) {
        if(_attributeDao == null) {
          _attributeDao = new attribute_Dao_Impl(this);
        }
        return _attributeDao;
      }
    }
  }

  @Override
  public background_Dao background_dao() {
    if (_backgroundDao != null) {
      return _backgroundDao;
    } else {
      synchronized(this) {
        if(_backgroundDao == null) {
          _backgroundDao = new background_Dao_Impl(this);
        }
        return _backgroundDao;
      }
    }
  }

  @Override
  public timetable_Dao timetable_dao() {
    if (_timetableDao != null) {
      return _timetableDao;
    } else {
      synchronized(this) {
        if(_timetableDao == null) {
          _timetableDao = new timetable_Dao_Impl(this);
        }
        return _timetableDao;
      }
    }
  }

  @Override
  public schedule_Dao schedule_dao() {
    if (_scheduleDao != null) {
      return _scheduleDao;
    } else {
      synchronized(this) {
        if(_scheduleDao == null) {
          _scheduleDao = new schedule_Dao_Impl(this);
        }
        return _scheduleDao;
      }
    }
  }

  @Override
  public route_PointDao route_pointDao() {
    if (_routePointDao != null) {
      return _routePointDao;
    } else {
      synchronized(this) {
        if(_routePointDao == null) {
          _routePointDao = new route_PointDao_Impl(this);
        }
        return _routePointDao;
      }
    }
  }

  @Override
  public speech_DAO speech_dao() {
    if (_speechDAO != null) {
      return _speechDAO;
    } else {
      synchronized(this) {
        if(_speechDAO == null) {
          _speechDAO = new speech_DAO_Impl(this);
        }
        return _speechDAO;
      }
    }
  }

  @Override
  public contacts_Dao contacts_dao() {
    if (_contactsDao != null) {
      return _contactsDao;
    } else {
      synchronized(this) {
        if(_contactsDao == null) {
          _contactsDao = new contacts_Dao_Impl(this);
        }
        return _contactsDao;
      }
    }
  }
}
