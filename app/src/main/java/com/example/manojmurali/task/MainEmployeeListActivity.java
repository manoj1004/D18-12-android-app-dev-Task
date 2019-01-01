package com.example.manojmurali.task;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainEmployeeListActivity extends AppCompatActivity {

    private Toolbar mToptoolbar;
    private EmployeeViewModel mEmployeeViewmodel;
    EmployeeListAdapter adapter;
    public static final int NEW_WORD_ACTIVITY_REQUEST = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainEmployeeListActivity.this, AddOrUpdateEmployees.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST);
            }
        });


        //creating the recycler view and setting the adapter to the recycler view in the layout
        RecyclerView recyclerview = findViewById(R.id.recycler_view_proj);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        final EmployeeListAdapter adapter = new EmployeeListAdapter(this);
        recyclerview.setAdapter(adapter);


//the whole view of this page of the app watches the changes to the list.
// makes sure it keeps the view updated to show newly added Employees.
        mEmployeeViewmodel = ViewModelProviders.of(this).

                get(EmployeeViewModel.class);
        mEmployeeViewmodel.getAllEmployees().

                observe(this, new Observer<List<Employee>>() {
                    @Override
                    public void onChanged(@Nullable final List<Employee> Employees) {
                        //Set the changed list of Employees in the recycler View based on the changes to the list
                        adapter.setmEmployees(Employees);
                    }

                });


//swipe functionality for each recycler view item
//
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder
                    viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mEmployeeViewmodel.delete(adapter.getEmployeeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainEmployeeListActivity.this, "Employee deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerview);


//on swipe send and receive data for update
        adapter.setOnItemClickListener(new EmployeeListAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(Employee Employee) {
                Intent intent = new Intent(MainEmployeeListActivity.this, AddOrUpdateEmployees.class);

                intent.putExtra(AddOrUpdateEmployees.EXTRA_ID, Employee.getId());
                intent.putExtra(AddOrUpdateEmployees.EXTRA_FNAME, Employee.getFname());
                intent.putExtra(AddOrUpdateEmployees.EXTRA_LNAME, Employee.getLname());
                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST);
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
            String fname = data.getStringExtra(AddOrUpdateEmployees.EXTRA_FNAME);
            String lname = data.getStringExtra(AddOrUpdateEmployees.EXTRA_LNAME);
            String doj = data.getStringExtra(AddOrUpdateEmployees.EXTRA_DOJ);
            String role = data.getStringExtra(AddOrUpdateEmployees.EXTRA_ROLE);
            String gender = data.getStringExtra(AddOrUpdateEmployees.EXTRA_GENDER);
            String type = data.getStringExtra(AddOrUpdateEmployees.EXTRA_TYPE);

            Employee EmployeeEntry = new Employee(fname, lname, role, type, gender, doj);
            mEmployeeViewmodel.insert(EmployeeEntry);

            Toast.makeText(this, "Employee Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddOrUpdateEmployees.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Employee can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String fname = data.getStringExtra(AddOrUpdateEmployees.EXTRA_FNAME);
            String lname = data.getStringExtra(AddOrUpdateEmployees.EXTRA_LNAME);
            String doj = data.getStringExtra(AddOrUpdateEmployees.EXTRA_DOJ);
            String role = data.getStringExtra(AddOrUpdateEmployees.EXTRA_ROLE);
            String gender = data.getStringExtra(AddOrUpdateEmployees.EXTRA_GENDER);
            String type = data.getStringExtra(AddOrUpdateEmployees.EXTRA_TYPE);

            Employee EmployeeEntry = new Employee(fname, lname, role, type, gender, doj);
            EmployeeEntry.setId(id);
            mEmployeeViewmodel.update(EmployeeEntry);
            Toast.makeText(this, "Employee Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Employee not added", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }


        switch (item.getItemId()) {
            case R.id.delete_all_:
                mEmployeeViewmodel.deleteAll();
                Toast.makeText(this, "All Employees deleted", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }


}
