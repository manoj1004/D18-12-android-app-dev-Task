package com.example.manojmurali.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class SaveNewProjects extends AppCompatActivity {

    public static final String EXTRA_PNAME = "com.example.manojmurali.task.EXTRA_PNAME";
    public static final String EXTRA_DOMAIN = "com.example.manojmurali.task.EXTRA_DOMAIN";
    public static final String EXTRA_HOURS = "com.example.manojmurali.task.EXTRA_HOURS";
    public static final String EXTRA_BEGINDATE = "com.example.manojmurali.task.EXTRA_BEGINDATE";
    public static final String EXTRA_ID = "com.example.manojmurali.task.EXTRA_ID";
    private EditText editProjectNameView;
    private EditText editDomainView;

    private EditText editHoursView;
    private EditText editBeginDateView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_new_projects);



        editProjectNameView = findViewById(R.id.edit_pname);
        editDomainView = findViewById(R.id.edit_domain);
        editHoursView = findViewById(R.id.edit_hours);
        editBeginDateView = findViewById(R.id.edit_begindate);

//important line to avoid App Compat issue
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Word");
            editProjectNameView.setText(intent.getStringExtra(EXTRA_PNAME));
            editDomainView.setText(intent.getStringExtra(EXTRA_DOMAIN));
            editHoursView.setText(intent.getStringExtra(EXTRA_HOURS));
            editBeginDateView.setText(intent.getStringExtra(EXTRA_BEGINDATE));


        } else {
            setTitle("Add New Word");
        }

    }

    private void saveWord() {
        String projectname = editProjectNameView.getText().toString();
        String domain = editDomainView.getText().toString();
        String hours = editHoursView.getText().toString();
        String begindate = editBeginDateView.getText().toString();


        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (projectname.trim().isEmpty() || domain.trim().isEmpty() || hours.trim().isEmpty() || begindate.trim().isEmpty()) {
            Toast.makeText(this, "Please insert an entry", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent data = new Intent();
        data.putExtra(EXTRA_PNAME, projectname);
        data.putExtra(EXTRA_DOMAIN, domain);
        data.putExtra(EXTRA_HOURS, hours);
        data.putExtra(EXTRA_BEGINDATE, begindate);
        data.putExtra(EXTRA_ID, id);


        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }


        setResult(RESULT_OK, data);
        finish();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_data:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

