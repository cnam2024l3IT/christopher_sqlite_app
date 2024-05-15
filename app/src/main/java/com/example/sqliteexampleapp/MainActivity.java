package com.example.sqliteexampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteexampleapp.databases.DBManager;
import com.example.sqliteexampleapp.models.Person;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button goToPersonFormBtn = findViewById(R.id.goToPersonFormBtn);

        dbManager = new DBManager(this);
        dbManager.open();

        ArrayList<Person> persons = dbManager.getPersonsList();

        for (Person person:persons) {
            Log.d("person", "onCreate: " + person);
        }

        goToPersonFormBtn.setOnClickListener(v -> MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(),
                PersonFormActivity.class)));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}