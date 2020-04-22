package com.zone.android.miskool_db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.zone.android.miskool_DAO.BusRoute_Dao;
import com.zone.android.miskool_DAO.Message_InMessage_det_Dao;
import com.zone.android.miskool_DAO.account_Dao;
import com.zone.android.miskool_DAO.alert_Dao;
import com.zone.android.miskool_DAO.attribute_Dao;
import com.zone.android.miskool_DAO.background_Dao;
import com.zone.android.miskool_DAO.config_Dao;
import com.zone.android.miskool_DAO.contacts_Dao;
import com.zone.android.miskool_DAO.messageIn_Dao;
import com.zone.android.miskool_DAO.messageOut_Dao;
import com.zone.android.miskool_DAO.message_Dao;
import com.zone.android.miskool_DAO.messagedet_Dao;
import com.zone.android.miskool_DAO.person_Dao;
import com.zone.android.miskool_DAO.route_PointDao;
import com.zone.android.miskool_DAO.schedule_Dao;
import com.zone.android.miskool_DAO.speech_DAO;
import com.zone.android.miskool_DAO.timetable_Dao;
import com.zone.android.miskool_DAO.token_Dao;
import com.zone.android.miskool_DAO.user_Dao;
import com.zone.android.miskool_Entitiy.Account;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.Bus_Route;
import com.zone.android.miskool_Entitiy.Config_det;
import com.zone.android.miskool_Entitiy.Contacts;
import com.zone.android.miskool_Entitiy.Message;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Entitiy.Message_det;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.RoutePoint;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Entitiy.Token_det;
import com.zone.android.miskool_Entitiy.User;
import com.zone.android.miskool_Entitiy.backGroundDB;
import com.zone.android.miskool_Entitiy.timetable;
import com.zone.android.miskool_Util.TypeTransmogrifiers;

/**
 * Created by Inspiron on 04-01-2018.
 */
@Database(entities = {User.class, Message_In.class,timetable.class, Message_det.class, Config_det.class, Token_det.class, Bus_Route.class,
        Attributes.class,Person_det.class, Alerts.class, Message.class, Message_Out.class, Account.class, backGroundDB.class, Schedule.class,RoutePoint.class,Speech.class,Contacts.class}, version = 2,exportSchema = false)

@TypeConverters({TypeTransmogrifiers.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract messageIn_Dao messageIn_dao();

    public abstract messagedet_Dao messagedet_Dao();

    public abstract Message_InMessage_det_Dao inMessage_det_dao();

    public abstract user_Dao user_dao();

    public abstract config_Dao config_dao();

    public abstract token_Dao token_dao();
    public  abstract BusRoute_Dao busRoute_dao();

    public abstract person_Dao person_dao();
    public abstract alert_Dao alerts_dao();

    public abstract message_Dao message_dao();

    //messageOut_dao
    public abstract messageOut_Dao messageOut_dao();

    public abstract account_Dao account_dao();

    public abstract attribute_Dao attribute_dao();

    public abstract background_Dao background_dao();

    public abstract timetable_Dao timetable_dao();

    public abstract schedule_Dao schedule_dao();

    public abstract route_PointDao route_pointDao();

    public abstract speech_DAO speech_dao();

    public  abstract contacts_Dao contacts_dao();


    private static AppDatabase INSTANCE;
    //private constructor.
    public AppDatabase(){}

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "miskool-database").build();

        }
        return INSTANCE;
    }



    public static void destroyInstance() {
        INSTANCE = null;
    }

    //adding migrationsss


   /* static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `Message_Det` (`inst_id` TEXT, "
                    + "`msg_id` TEXT,`msg_type` TEXT,`msg_time` TEXT, PRIMARY KEY(`inst_id`))");
        }
    };*/

}