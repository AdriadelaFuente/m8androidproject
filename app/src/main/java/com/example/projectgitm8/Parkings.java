package com.example.projectgitm8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Parkings extends AppCompatActivity implements View.OnClickListener {

    Parking[] lParkings = new Parking[3];
    CardView parking1, parking2, parking3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkings);

        loadParkings();

        parking1 = findViewById(R.id.parking1);
        parking1.setOnClickListener(this);

        parking2 = findViewById(R.id.parking2);
        parking2.setOnClickListener(this);

        parking3 = findViewById(R.id.parking3);
        parking3.setOnClickListener(this);

        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.parkingsLayout);
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

    private void loadParkings() {
        TextView name, totalPlaces, availablePlaces;
        lParkings[0] = new Parking("Aparcament Municipal Gratuït", 271, 36, 41.61437f, 2.28641f);
        name = findViewById(R.id.parking1Name);
        totalPlaces = findViewById(R.id.parking1TotalPlaces);
        availablePlaces = findViewById(R.id.parking1AvailablePlaces);
        name.setText(lParkings[0].getNom());
        totalPlaces.setText(buildPlacesString(0, lParkings[0].getPlacesTotals()));
        availablePlaces.setText(buildPlacesString(1, lParkings[0].getPlacesDisponibles()));

        lParkings[1] = new Parking("Parking Ponent", 82, 14, 41.61181f, 2.28668f);
        name = findViewById(R.id.parking2Name);
        totalPlaces = findViewById(R.id.parking2TotalPlaces);
        availablePlaces = findViewById(R.id.parking2AvailablePlaces);
        name.setText(lParkings[1].getNom());
        totalPlaces.setText(buildPlacesString(0, lParkings[1].getPlacesTotals()));
        availablePlaces.setText(buildPlacesString(1, lParkings[1].getPlacesDisponibles()));

        lParkings[2] = new Parking("Parking Hospital de Granollers", 123, 78, 41.61399f, 2.29733f);
        name = findViewById(R.id.parking3Name);
        totalPlaces = findViewById(R.id.parking3TotalPlaces);
        availablePlaces = findViewById(R.id.parking3AvailablePlaces);
        name.setText(lParkings[2].getNom());
        totalPlaces.setText(buildPlacesString(0, lParkings[2].getPlacesTotals()));
        availablePlaces.setText(buildPlacesString(1, lParkings[2].getPlacesDisponibles()));
    }

    private String buildPlacesString(int type, int numPlaces) {
        String s = numPlaces + " places";
        if (type==1) s += " disponibles";
        return s;
    }
    @Override
    public void onClick(View v) {
        Parking parkingSeleccionat = lParkings[0];
        if (v==parking2) parkingSeleccionat = lParkings[1];
        else if (v==parking3) parkingSeleccionat = lParkings[2];

        Intent intent = new Intent(Parkings.this, ParkingDetail.class);
        Bundle sacInformacio = new Bundle();
        sacInformacio.putString("name", parkingSeleccionat.getNom());
        sacInformacio.putFloat("longX", parkingSeleccionat.getLongX());
        sacInformacio.putFloat("longY", parkingSeleccionat.getLongY());
        intent.putExtras(sacInformacio);
        startActivity(intent);
    }
}

class Parking {
    private final String nom;
    private final int placesTotals;
    private final int placesDisponibles;

    private final float longX;
    private final float longY;

    public Parking(String nom, int placesTotals, int placesDisponibles, float longX, float longY) {
        this.nom = nom;
        this.placesTotals = placesTotals;
        this.placesDisponibles = placesDisponibles;
        this.longX = longX;
        this.longY = longY;
    }

    public String getNom() {
        return nom;
    }

    public int getPlacesTotals() {
        return placesTotals;
    }

    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public float getLongX() {
        return longX;
    }

    public float getLongY() {
        return longY;
    }
}