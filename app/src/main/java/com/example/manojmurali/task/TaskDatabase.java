package com.example.manojmurali.task;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.lang.String;
import java.text.SimpleDateFormat;


@Database(entities = {Employee.class, Project.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract EmployeeDao employeeDao();

    public abstract ProjectDao projectDao();


    private static TaskDatabase INSTANCE;

    static synchronized TaskDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (TaskDatabase.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskDatabase.class, "Taskdb")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDbCallback)
                            .build();

                }
            }
        }
        return INSTANCE;

    }

    public static RoomDatabase.Callback sRoomDbCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsync(INSTANCE).execute();

        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final EmployeeDao memployeeDao;
        private final ProjectDao mprojectDao;

        PopulateDbAsync(TaskDatabase db) {
            memployeeDao = db.employeeDao();
            mprojectDao = db.projectDao();
        }

        protected Void doInBackground(final Void... params) {
            memployeeDao.deleteAll();
            mprojectDao.deleteAll();


            memployeeDao.insert(new Employee("Manoj", "Murali", "QA Analyst", "Consultant", "M", "01/01/2018"));
            mprojectDao.insert(new Project("Member Enrollment", "Membership", "30", "05/01/2016"));

            return null;


        }

    }


}
