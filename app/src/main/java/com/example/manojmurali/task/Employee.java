package com.example.manojmurali.task;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;


@Entity(tableName = "employee_table")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String fname;
    public String lname;
    public String jobrole;
    public String type;
    public String gender;
    public String doj;

    public Employee(String fname, String lname, String jobrole, String type, String gender, String doj) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.type = type;
        this.doj = doj;
        this.jobrole = jobrole;

    }

    public String getJobrole() {
        return jobrole;
    }

    public String getGender() {
        return gender;
    }

    public String getDoj() {
        return doj;
    }

    public String getType() {
        return type;
    }

    public String getLname() {
        return lname;
    }

    public String getFname() {
        return fname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
