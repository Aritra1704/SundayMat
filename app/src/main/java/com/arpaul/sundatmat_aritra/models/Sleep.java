package com.arpaul.sundatmat_aritra.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "sleep_table")
public class Sleep extends BaseDO {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String day = "";
    private String timeperiod = "";

    @Override
    public String toString() {
        return "Sleep{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", timeperiod='" + timeperiod + '\'' +
                ", action='" + action + '\'' +
                '}';
    }

    private String action = "";

    public Sleep(){}

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


}
