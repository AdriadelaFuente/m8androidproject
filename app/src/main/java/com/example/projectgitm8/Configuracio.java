package com.example.projectgitm8;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Configuracio extends AppCompatActivity implements View.OnClickListener {
    int initialColor;
    Button btnPickBgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);

        btnPickBgColor = findViewById(R.id.bgColorPicker);
        btnPickBgColor.setOnClickListener(this);

        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        initialColor = sharedPref.getInt("bgColor", Color.WHITE);
        LinearLayout linearLayout = findViewById(R.id.configuracioLayout);
        linearLayout.setBackgroundColor(initialColor);
    }

    @Override
    public void onClick(View v) {
        openColorPicker();
    }

    public void openColorPicker() {
        AmbilWarnaDialog colorDialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {}

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                saveColorToSharedPreferences(color);
                EventBus.getDefault().post(new BackgroundColorChangedEvent(color));
            }
        });
        colorDialog.show();
    }

    private void saveColorToSharedPreferences(int color) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("bgColor", color);
        editor.apply();

        LinearLayout linearLayout = findViewById(R.id.configuracioLayout);
        linearLayout.setBackgroundColor(color);
    }
}