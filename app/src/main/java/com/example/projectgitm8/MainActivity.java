package com.example.projectgitm8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnRestaurants,
                btnBusinesses,
                btnHotels,
                btnWeather,
                btnMovies,
                btnParkings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRestaurants = (ImageButton)findViewById(R.id.restaurants);
        btnRestaurants.setOnClickListener(this);
        btnBusinesses = (ImageButton)findViewById(R.id.businesses);
        btnBusinesses.setOnClickListener(this);
        btnHotels = (ImageButton)findViewById(R.id.hotels);
        btnHotels.setOnClickListener(this);
        btnWeather = (ImageButton)findViewById(R.id.weather);
        btnWeather.setOnClickListener(this);
        btnMovies = (ImageButton)findViewById(R.id.movies);
        btnMovies.setOnClickListener(this);
        btnParkings = (ImageButton)findViewById(R.id.parkings);
        btnParkings.setOnClickListener(this);
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
        startActivity(intent);
    }
}