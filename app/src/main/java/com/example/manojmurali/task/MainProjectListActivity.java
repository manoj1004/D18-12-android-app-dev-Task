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

public class MainProjectListActivity extends AppCompatActivity {

    private Toolbar mToptoolbar;
    private ProjectViewModel mProjectViewmodel;
    ProjectListAdapter adapter;
    public static final int NEW_WORD_ACTIVITY_REQUEST = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_project_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainProjectListActivity.this, SaveNewProjects.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST);
            }
        });


        //creating the recycler view and setting the adapter to the recycler view in the layout
        RecyclerView recyclerview = findViewById(R.id.recycler_view_proj);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        final ProjectListAdapter adapter = new ProjectListAdapter(this);
        recyclerview.setAdapter(adapter);


//the whole view of this page of the app watches the changes to the list.
// makes sure it keeps the view updated to show newly added projects.
        mProjectViewmodel = ViewModelProviders.of(this).get(ProjectViewModel.class);
        mProjectViewmodel.getAllProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable final List<Project> projects) {
                //Set the changed list of projects in the recycler View based on the changes to the list
                adapter.setmProjects(projects);
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
                mProjectViewmodel.delete(adapter.getProjectAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainProjectListActivity.this, "Project deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerview);


//on swipe send and receive data for update
        adapter.setOnItemClickListener(new ProjectListAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(Project project) {
                Intent intent = new Intent(MainProjectListActivity.this, SaveNewProjects.class);

                intent.putExtra(SaveNewProjects.EXTRA_ID, project.getId());
                intent.putExtra(SaveNewProjects.EXTRA_PNAME, project.getPname());
                intent.putExtra(SaveNewProjects.EXTRA_DOMAIN, project.getDomain());


                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST);
            }
        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
            String project = data.getStringExtra(SaveNewProjects.EXTRA_PNAME);
            String domain = data.getStringExtra(SaveNewProjects.EXTRA_DOMAIN);
            String hours = data.getStringExtra(SaveNewProjects.EXTRA_HOURS);
            String begindate = data.getStringExtra(SaveNewProjects.EXTRA_BEGINDATE);

            Project projectEntry = new Project(project, domain, hours, begindate);
            mProjectViewmodel.insert(projectEntry);

            Toast.makeText(this, "Project Added", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(SaveNewProjects.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Project can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String project = data.getStringExtra(SaveNewProjects.EXTRA_PNAME);
            String domain = data.getStringExtra(SaveNewProjects.EXTRA_DOMAIN);
            String hours = data.getStringExtra(SaveNewProjects.EXTRA_HOURS);
            String begindate = data.getStringExtra(SaveNewProjects.EXTRA_BEGINDATE);

            Project projectEntry = new Project(project, domain, hours, begindate);
            projectEntry.setId(id);
            mProjectViewmodel.update(projectEntry);
            Toast.makeText(this, "Project Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Project not added", Toast.LENGTH_SHORT).show();
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
                mProjectViewmodel.deleteAll();
                Toast.makeText(this, "All projects deleted", Toast.LENGTH_SHORT).show();

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}



