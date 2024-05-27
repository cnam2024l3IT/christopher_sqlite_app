package com.example.sqliteexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteexampleapp.adapters.PersonListViewAdapter;
import com.example.sqliteexampleapp.databases.DBManager;

public class PersonIndexActivity extends AppCompatActivity {

    ListView personsListView;
    Button goToMainBtn, goToPersonFormBtn;
    Context context;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_person_index);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        dbManager = new DBManager(context);
        dbManager.open();
        personsListView = findViewById(R.id.personsListView);
        goToMainBtn = findViewById(R.id.goToMainBtn1);
        goToPersonFormBtn = findViewById(R.id.goToPersonFormBtn);

        personsListView.setAdapter(new PersonListViewAdapter(context, dbManager.getPersonsList()));

        goToMainBtn.setOnClickListener(v -> startActivity(new Intent(context, MainActivity.class)));
        goToPersonFormBtn.setOnClickListener(v -> startActivity(new Intent(context, PersonFormActivity.class)));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}