package com.example.manojmurali.task;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@Entity(tableName = "project_table")
public class Project {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String pname;
    public String domain;
    public String hours;
    public String  begindate;

    public Project(String pname, String domain, String hours, String  begindate) {

        this.pname = pname;
        this.domain = domain;
        this.hours = hours;
        this.begindate = begindate;

    }

    public String getPname() {
        return pname;
    }

    public String getDomain() {
        return domain;
    }

    public String getHours() {
        return hours;
    }

    public String getBegindate() {
        return begindate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

