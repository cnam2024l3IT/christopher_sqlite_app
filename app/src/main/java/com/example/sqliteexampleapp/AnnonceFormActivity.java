package com.example.sqliteexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteexampleapp.databases.DBManager;
import com.example.sqliteexampleapp.models.Annonce;
import com.example.sqliteexampleapp.models.Person;

public class AnnonceFormActivity extends AppCompatActivity {

    EditText personCtrl, titleCtrl, priceCtrl, descriptionCtrl, datePublicationCtrl, dateFinPublicationCtrl;
    Button goToAnnonceIndexBtn, saveBtn;
    Context context;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_annonce_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        personCtrl = findViewById(R.id.annoncePersonCtrl);
        titleCtrl = findViewById(R.id.annonceTitleCtrl);
        priceCtrl = findViewById(R.id.annoncePriceCtrl);
        descriptionCtrl = findViewById(R.id.annonceDescriptionCtrl);
        datePublicationCtrl = findViewById(R.id.annonceDatePublicationCtrl);
        dateFinPublicationCtrl = findViewById(R.id.annonceDateFinPublicationCtrl);
        saveBtn = findViewById(R.id.saveAnnonceBtn);
        goToAnnonceIndexBtn = findViewById(R.id.goToAnnonceIndexBtn2);

        dbManager = new DBManager(context);
        dbManager.open();

        goToAnnonceIndexBtn.setOnClickListener(v -> startActivity(new Intent(context, AnnonceIndexActivity.class)));

        saveBtn.setOnClickListener(this::addAnnonce);
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }

    private void addAnnonce(View v) {
        Person person = new Person(Integer.parseInt(personCtrl.getText().toString()));
        String title = titleCtrl.getText().toString();
        int price = Integer.parseInt(priceCtrl.getText().toString());
        String description = descriptionCtrl.getText().toString();
        String datePublication = datePublicationCtrl.getText().toString();
        String dateFinPublication = dateFinPublicationCtrl.getText().toString();
        Annonce annonce = new Annonce(person, title, price, description, datePublication, dateFinPublication);
        long _id = dbManager.insertAnnonce(annonce);
        Log.d("annonce id", "onClick: " + _id);
    }
}