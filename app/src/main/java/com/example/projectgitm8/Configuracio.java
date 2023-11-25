package com.example.projectgitm8;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
        int initialColor = Color.WHITE;
        AmbilWarnaDialog colorDialog = new AmbilWarnaDialog(this, initialColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                // working on it
            }
        });
        colorDialog.show();
    }
}