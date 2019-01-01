package com.example.manojmurali.task;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button projcardView;
    private Button empcardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        projcardView = (Button) findViewById(R.id.proj_button);
        empcardView = (Button) findViewById(R.id.emp_button);


        projcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainProjectListActivity.class);
                startActivity(intent);
            }
        });


        empcardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainEmployeeListActivity.class);
                startActivity(intent);
            }
        });


    }
}