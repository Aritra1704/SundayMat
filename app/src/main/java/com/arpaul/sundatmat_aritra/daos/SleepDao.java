package com.arpaul.sundatmat_aritra.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.arpaul.sundatmat_aritra.models.Sleep;

import java.util.List;

@Dao
public interface SleepDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert
    long insert(Sleep user);

    @Query("DELETE FROM sleep_table")
    void deleteAll();

    @Query("SELECT * FROM sleep_table")
    LiveData<List<Sleep>> getAllSleeps();
}
