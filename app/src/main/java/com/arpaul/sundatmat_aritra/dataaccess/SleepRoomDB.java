package com.arpaul.sundatmat_aritra.dataaccess;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.arpaul.sundatmat_aritra.daos.SleepDao;
import com.arpaul.sundatmat_aritra.daos.UserSleepDao;
import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.models.UserSleep;

@Database(entities = {Sleep.class, UserSleep.class}, version = 1, exportSchema = false)
public abstract class SleepRoomDB extends RoomDatabase {
    public abstract SleepDao sleepDao();
    public abstract UserSleepDao userSleepDao();

    private static SleepRoomDB INSTANCE;

    public static SleepRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SleepRoomDB.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SleepRoomDB.class, "sleep_db")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    private static Callback sRoomDatabaseCallback = new Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}
