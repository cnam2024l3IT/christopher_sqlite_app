package com.example.sqliteexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    private EditText lastNameCtrl, firstNameCtrl;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_personne_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lastNameCtrl = findViewById(R.id.personLastNameCtrl);
        firstNameCtrl = findViewById(R.id.personFirstNameCtrl);
        Button saveBtn = findViewById(R.id.savePersonBtn);
        Button goToMainBtn = findViewById(R.id.goToMainBtn);

        dbManager = new DBManager(this);
        dbManager.open();

        saveBtn.setOnClickListener(v -> {
            Person person = new Person(lastNameCtrl.getText().toString(), firstNameCtrl.getText().toString());
            long personId = dbManager.insertPersonne(person);
            person.set_id(personId);
            Log.d("createPerson", "person id: " + person);
        });

        goToMainBtn.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), MainActivity.class)));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}