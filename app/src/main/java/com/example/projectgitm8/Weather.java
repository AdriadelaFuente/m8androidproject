package com.example.projectgitm8;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Weather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        View linearLayout = findViewById(R.id.weaterLayout);
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