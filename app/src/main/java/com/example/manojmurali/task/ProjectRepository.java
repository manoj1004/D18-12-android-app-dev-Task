package com.example.manojmurali.task;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import java.util.List;

public class ProjectRepository {

    private ProjectDao projectDao;
    public LiveData<List<Project>> mAllProjects;

    ProjectRepository(Application application) {
        TaskDatabase db = TaskDatabase.getDatabase(application);
        projectDao = db.projectDao();
        mAllProjects = projectDao.getAllProjectsDetails();
    }

    public void insert(Project project) {
        new insertAsyncTask(projectDao).execute(project);
    }

    public void update(Project project) {
        new updateAsyncTask(projectDao).execute(project);
    }

    public void delete(Project project) {
        new deleteAllAsyncTask(projectDao).execute(project);
    }

    public void deleteAll() {
        new deleteAllAsyncTask(projectDao).execute();
    }

    public LiveData<List<Project>> getmAllProjects() {
        return mAllProjects;
    }


    private static class insertAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mprojectDao;

        insertAsyncTask(ProjectDao projectDao) {
            this.mprojectDao = projectDao;
        }

        protected Void doInBackground(final Project... params) {
            mprojectDao.insert(params[0]);
            return null;
        }
    }


    private static class updateAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mprojectDao;

        updateAsyncTask(ProjectDao projectDao) {
            this.mprojectDao = projectDao;
        }

        protected Void doInBackground(final Project... params) {
            mprojectDao.update(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mprojectDao;

        deleteAsyncTask(ProjectDao projectDao) {
            this.mprojectDao = projectDao;
        }

        protected Void doInBackground(final Project... params) {
            mprojectDao.delete(params[0]);
            return null;
        }
    }


    private static class deleteAllAsyncTask extends AsyncTask<Project, Void, Void> {

        private ProjectDao mprojectDao;

        deleteAllAsyncTask(ProjectDao projectDao) {
            this.mprojectDao = projectDao;
        }

        protected Void doInBackground(final Project... params) {
            mprojectDao.deleteAll();
            return null;
        }
    }

}




