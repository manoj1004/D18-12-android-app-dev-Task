package com.example.manojmurali.task;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {

    private ProjectRepository projectRepository;

    ProjectViewModel(Application application) {
        super(application);
        projectRepository = new ProjectRepository(application);
    }

    void insert(Project project) {
        projectRepository.insert(project);
    }

    void update(Project project) {
        projectRepository.update(project);
    }

    void delete(Project project) {
        projectRepository.delete(project);
    }

    void deleteAll() {
        projectRepository.deleteAll();
    }

    LiveData<List<Project>> getAllProjects() {
        return projectRepository.getmAllProjects();

    }
}
