package com.arpaul.sundatmat_aritra.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.models.UserSleep;

import java.util.List;

@Dao
public interface UserSleepDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    long insert(UserSleep user);

    @Query("DELETE FROM user_sleep_table")
    void deleteAll();

    @Query("SELECT * FROM user_sleep_table")
    LiveData<List<UserSleep>> getAllUserSleepRecs();
}
