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

import com.example.sqliteexampleapp.adapters.AnnonceListViewAdapter;
import com.example.sqliteexampleapp.databases.DBManager;

public class AnnonceIndexActivity extends AppCompatActivity {

    ListView annoncesListView;
    Button goToMainBtn, goToAnnonceFormBtn;
    Context context;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_annonce_index);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = this;
        dbManager = new DBManager(context);
        dbManager.open();
        annoncesListView = findViewById(R.id.annoncesListView);
        goToMainBtn = findViewById(R.id.goToMainBtn2);
        goToAnnonceFormBtn = findViewById(R.id.goToAnnonceFormBtn);

        AnnonceListViewAdapter adapter = new AnnonceListViewAdapter(context, dbManager.getAnnoncesList());
        annoncesListView.setAdapter(adapter);

        goToMainBtn.setOnClickListener(v -> startActivity(new Intent(context, MainActivity.class)));
        goToAnnonceFormBtn.setOnClickListener(v -> startActivity(new Intent(context, AnnonceFormActivity.class)));
    }

    @Override
    protected void onDestroy() {
        dbManager.close();
        super.onDestroy();
    }
}