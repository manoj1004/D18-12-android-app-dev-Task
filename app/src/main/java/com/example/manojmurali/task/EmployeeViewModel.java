package com.example.manojmurali.task;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository employeeRepository;

    EmployeeViewModel(Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
    }

    void insert(Employee employee) {
        employeeRepository.insert(employee);
    }

    void update(Employee employee) {
        employeeRepository.update(employee);
    }

    void delete(Employee employee) {
        employeeRepository.delete(employee);
    }

    void deleteAll() {
        employeeRepository.deleteAll();
    }

    LiveData<List<Employee>> getAllEmployees() {
        return employeeRepository.getmAllEmployees();

    }
}
