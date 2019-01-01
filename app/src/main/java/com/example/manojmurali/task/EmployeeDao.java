package com.example.manojmurali.task;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import android.arch.persistence.room.Query;

@Dao
public interface EmployeeDao {

    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employee_table")
    void deleteAll();

    @Query("SELECT * FROM employee_table order by fname ASC")
    LiveData<List<Employee>> getAllEmployeeDetails();

}
