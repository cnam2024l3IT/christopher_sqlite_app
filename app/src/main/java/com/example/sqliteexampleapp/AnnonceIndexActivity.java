package com.example.sqliteexampleapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnnonceIndexActivity extends AppCompatActivity {

    Button goToMainBtn, goToAnnonceFormBtn;
    Context context;

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
        goToMainBtn = findViewById(R.id.goToMainBtn2);
        goToAnnonceFormBtn = findViewById(R.id.goToAnnonceFormBtn);

        goToMainBtn.setOnClickListener(v -> startActivity(new Intent(context, MainActivity.class)));
        goToAnnonceFormBtn.setOnClickListener(v -> startActivity(new Intent(context, AnnonceFormActivity.class)));
    }
}