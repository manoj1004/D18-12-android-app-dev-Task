package com.example.manojmurali.task;

import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Assignment extends AppCompatActivity {

    private ArrayList<Employee> employeeArrayList;
    private SpinnerAdapter.EmployeeSpinnerAdapter empSpinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//create a spinner object in order to set the adapters for display
        Spinner spin = (Spinner) findViewById(R.id.spinner);
//set adapter to List in order to get the list view
        empSpinnerAdapter = new SpinnerAdapter.EmployeeSpinnerAdapter(this, employeeArrayList);
        spin.setAdapter(empSpinnerAdapter);

//define the action on selecting from the dropdown list
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
