package com.example.manojmurali.task;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddOrUpdateEmployees extends AppCompatActivity {

    public static final String EXTRA_FNAME = "com.example.manojmurali.task.EXTRA_FNAME";
    public static final String EXTRA_LNAME = "com.example.manojmurali.task.EXTRA_LNAME";
    public static final String EXTRA_ROLE = "com.example.manojmurali.task.EXTRA_ROLE";
    public static final String EXTRA_TYPE = "com.example.manojmurali.task.EXTRA_TYPE";
    public static final String EXTRA_GENDER = "com.example.manojmurali.task.EXTRA_GENDER";
    public static final String EXTRA_DOJ = "com.example.manojmurali.task.EXTRA_DOJ";
    public static final String EXTRA_ID = "com.example.manojmurali.task.EXTRA_ID";

    private EditText editFirstView;
    private EditText editLastView;
    private EditText editRoleView;
    private EditText editTypeView;
    private EditText editGenderView;
    private EditText editJoinDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update_employees);


        editFirstView = findViewById(R.id.edit_fname);
        editLastView = findViewById(R.id.edit_lname);
        editRoleView = findViewById(R.id.edit_role);
        editTypeView = findViewById(R.id.edit_type);
        editGenderView = findViewById(R.id.edit_gender);
        editJoinDate = findViewById(R.id.edit_doj);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Word");

            editFirstView.setText(intent.getStringExtra(EXTRA_FNAME));
            editLastView.setText(intent.getStringExtra(EXTRA_LNAME));
            editRoleView.setText(intent.getStringExtra(EXTRA_ROLE));
            editTypeView.setText(intent.getStringExtra(EXTRA_TYPE));
            editGenderView.setText(intent.getStringExtra(EXTRA_GENDER));
            editJoinDate.setText(intent.getStringExtra(EXTRA_DOJ));


        } else {
            setTitle("Add New Word");
        }

    }

    private void saveWord() {
        String fname = editFirstView.getText().toString();
        String lname = editLastView.getText().toString();
        String role = editRoleView.getText().toString();
        String type = editTypeView.getText().toString();
        String gender = editGenderView.getText().toString();
        String joindate = editJoinDate.getText().toString();


        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (fname.trim().isEmpty() || lname.trim().isEmpty()
                || role.trim().isEmpty() || type.trim().isEmpty()
                || gender.trim().isEmpty() || joindate.trim().isEmpty()) {
            Toast.makeText(this, "Please type an entry to save", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent data = new Intent();
        data.putExtra(EXTRA_FNAME, fname);
        data.putExtra(EXTRA_LNAME, lname);
        data.putExtra(EXTRA_ROLE, role);
        data.putExtra(EXTRA_TYPE, type);
        data.putExtra(EXTRA_GENDER, gender);
        data.putExtra(EXTRA_DOJ, joindate);
        data.putExtra(EXTRA_ID, id);


        if (id != -1)

        {
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
