package com.example.manojmurali.task;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


import java.util.List;

public class EmployeeRepository {

    private EmployeeDao employeeDao;
    public LiveData<List<Employee>> mAllEmployees;

    EmployeeRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        employeeDao = db.employeeDao();
        mAllEmployees = employeeDao.getAllEmployeeDetails();
    }

    public void insert(Employee employee) {
        new insertAsyncTask(employeeDao).execute(employee);
    }


    public void update(Employee employee) {
        new EmployeeRepository.updateAsyncTask(employeeDao).execute(employee);
    }

    public void delete(Employee employee) {
        new EmployeeRepository.deleteAllAsyncTask(employeeDao).execute(employee);
    }

    public void deleteAll() {
        new EmployeeRepository.deleteAllAsyncTask(employeeDao).execute();
    }

    public LiveData<List<Employee>> getmAllEmployees() {
        return mAllEmployees;
    }

        private static class insertAsyncTask extends AsyncTask<Employee, Void, Void> {

            private EmployeeDao memployeeDao;

            insertAsyncTask(EmployeeDao employeeDao) {
                this.memployeeDao = employeeDao;
            }

            protected Void doInBackground(final Employee... params) {
                memployeeDao.insert(params[0]);
                return null;
            }
        }


        private static class updateAsyncTask extends AsyncTask<Employee, Void, Void> {

            private EmployeeDao memployeeDao;

            updateAsyncTask(EmployeeDao employeeDao) {
                this.memployeeDao = employeeDao;
            }

            protected Void doInBackground(final Employee... params) {
                memployeeDao.update(params[0]);
                return null;
            }
        }

        private static class deleteAsyncTask extends AsyncTask<Employee, Void, Void> {

            private EmployeeDao memployeeDao;

            deleteAsyncTask(EmployeeDao employeeDao) {
                this.memployeeDao = employeeDao;
            }

            protected Void doInBackground(final Employee... params) {
                memployeeDao.delete(params[0]);
                return null;
            }
        }


        private static class deleteAllAsyncTask extends AsyncTask<Employee, Void, Void> {

            private EmployeeDao memployeeDao;

            deleteAllAsyncTask(EmployeeDao employeeDao) {
                this.memployeeDao = employeeDao;
            }

            protected Void doInBackground(final Employee... params) {
                memployeeDao.deleteAll();
                return null;
            }
        }
    }

    




