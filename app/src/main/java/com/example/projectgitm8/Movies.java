package com.example.projectgitm8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class Movies extends AppCompatActivity implements View.OnClickListener {

    Button btnHorari1, btnHorari2, btnHorari3, btnHorari4, btnHorari5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        btnHorari1 = findViewById(R.id.horariFnaf);
        btnHorari2 = findViewById(R.id.horariJuegos);
        btnHorari3 = findViewById(R.id.horariHypnotic);
        btnHorari4 = findViewById(R.id.horariMarvel);
        btnHorari5 = findViewById(R.id.horariPatrullaCanina);
        btnHorari1.setOnClickListener(this);
        btnHorari2.setOnClickListener(this);
        btnHorari3.setOnClickListener(this);
        btnHorari4.setOnClickListener(this);
        btnHorari5.setOnClickListener(this);

        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    @Override
    public void onClick(View v) {
        Bundle huevo = new Bundle();
        Intent intent = null;
        intent = new Intent(this, HorariMovie.class);
        if (v == btnHorari1) {
            huevo.putString("type", "Five Nights At Freddy's");
        } else if (v == btnHorari2) {
            huevo.putString("type", "Los Juegos del Hambre");
        } else if (v == btnHorari3) {
            huevo.putString("type", "Hypnotic");
        } else if (v == btnHorari4) {
            huevo.putString("type", "The Marvel");
        } else if (v == btnHorari5) {
            huevo.putString("type", "La Patrulla Canina");
        }
        intent.putExtras(huevo);
        startActivity(intent);
    }

    private void onItemClickFunction(String itemName) {
        // Puedes hacer lo que quieras con el nombre del elemento, por ejemplo, mostrar un Toast
        Toast.makeText(this, "Clic en: " + itemName, Toast.LENGTH_SHORT).show();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.moviesLayout);
        linearLayout.setBackgroundColor(sharedPref.getInt("bgColor", Color.WHITE));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackgroundColorChanged(BackgroundColorChangedEvent event) {
        int newColor = event.getNewColor();
        View layout = findViewById(R.id.mainLayout);
        layout.setBackgroundColor(newColor);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}