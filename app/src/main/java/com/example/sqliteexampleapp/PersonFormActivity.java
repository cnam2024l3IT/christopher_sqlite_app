package com.example.sqliteexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteexampleapp.databases.DBManager;
import com.example.sqliteexampleapp.models.Person;

public class PersonFormActivity extends AppCompatActivity {

    EditText lastNameCtrl, firstNameCtrl;

    private DBManager dbManager;

    Button saveBtn, goToPersonIndexBtn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_person_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        lastNameCtrl = findViewById(R.id.personLastNameCtrl);
        firstNameCtrl = findViewById(R.id.personFirstNameCtrl);
        saveBtn = findViewById(R.id.savePersonBtn);
        goToPersonIndexBtn = findViewById(R.id.goToPersonIndexBtn2);

        dbManager = new DBManager(context);
        dbManager.open();

        saveBtn.setOnClickListener(this::addPerson);

        goToPersonIndexBtn.setOnClickListener(v -> startActivity(new Intent(context, PersonIndexActivity.class)));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }

    private void addPerson(View v) {
        dbManager.insertPersonne(new Person(lastNameCtrl.getText().toString(), firstNameCtrl.getText().toString()));
    }
}