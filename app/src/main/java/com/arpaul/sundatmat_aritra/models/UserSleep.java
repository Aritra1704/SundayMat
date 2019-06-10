package com.arpaul.sundatmat_aritra.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "user_sleep_table")
public class UserSleep extends BaseDO {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String day = "";
    private String timeperiod = "";
    private String startTime = "";
    private String endTime = "";
    private String sleepType = "";


    public UserSleep(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(String timeperiod) {
        this.timeperiod = timeperiod;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSleepType() {
        return sleepType;
    }

    public void setSleepType(String sleepType) {
        this.sleepType = sleepType;
    }

    @Override
    public String toString() {
        return "UserSleep{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", timeperiod='" + timeperiod + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", sleepType='" + sleepType + '\'' +
                '}';
    }
}
