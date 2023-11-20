package com.example.projectgitm8;


import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Configuracio extends AppCompatActivity implements View.OnClickListener {
    Button btnPickBgColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);

        btnPickBgColor = findViewById(R.id.bgColorPicker);
        btnPickBgColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        openColorPicker();
    }

    public void openColorPicker() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("app_settings", 0); // 0 - for private mode
        int initialColor = pref.getInt("bgColor", -1);
        //int initialColor = ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary);
        AmbilWarnaDialog colorDialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                SharedPreferences pref = getApplicationContext().getSharedPreferences("app_settings", 0); // 0 - for private mode
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("bgColor", color);
                //editor.commit();
                editor.apply();

            }
        });
        colorDialog.show();
    }
}