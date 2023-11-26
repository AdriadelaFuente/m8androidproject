package com.example.projectgitm8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnRestaurants,
                btnBusinesses,
                btnHotels,
                btnWeather,
                btnMovies,
                btnParkings;
    Button btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRestaurants = findViewById(R.id.restaurants);
        btnRestaurants.setOnClickListener(this);
        btnBusinesses = findViewById(R.id.businesses);
        btnBusinesses.setOnClickListener(this);
        btnHotels = findViewById(R.id.hotels);
        btnHotels.setOnClickListener(this);
        btnWeather = findViewById(R.id.weather);
        btnWeather.setOnClickListener(this);
        btnMovies = findViewById(R.id.movies);
        btnMovies.setOnClickListener(this);
        btnParkings = findViewById(R.id.parkings);
        btnParkings.setOnClickListener(this);
        btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(this);

        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.mainLayout);
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

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v==btnRestaurants) intent = new Intent(this, Restaurants.class);
        else if (v==btnBusinesses) intent = new Intent(this, Businesses.class);
        else if (v==btnHotels) intent = new Intent(this, Hotels.class);
        else if (v==btnWeather) intent = new Intent(this, Weather.class);
        else if (v==btnMovies) intent = new Intent(this, Movies.class);
        else if (v==btnParkings) intent = new Intent(this, Parkings.class);
        else if (v==btnSettings) intent = new Intent(this, Configuracio.class);
        startActivity(intent);
    }
}