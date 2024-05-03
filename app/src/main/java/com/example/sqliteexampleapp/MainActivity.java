package com.example.sqliteexampleapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sqliteexampleapp.databases.DBManager;
import com.example.sqliteexampleapp.models.Annonce;
import com.example.sqliteexampleapp.models.Personne;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

        // ajouter une personne : table + manager table ; une personne à 1 ou N annonce
        // personne = propriétaire annonce

        dbManager = new DBManager(this);
        dbManager.open();

        Personne personne = new Personne("REEVES", "Keanu");
        long personneId = dbManager.insertPersonne(personne);
        personne.set_id(personneId);
        Log.d("createPersonne", "personne id: " + personne);

        Annonce annonce = new Annonce(personne, "Annonce 1", 100, "Annonce test 1", "2024-04-24",
            "");
        long annonceId = dbManager.insertAnnonce(annonce);
        annonce.set_id(annonceId);
        Log.d("createAnnonce", "annonce id: " + annonce);
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}