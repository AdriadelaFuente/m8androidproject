package com.example.projectgitm8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import yuku.ambilwarna.AmbilWarnaDialog;

public class Configuracio extends AppCompatActivity implements View.OnClickListener {

    Button btnPickFontColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracio);

        btnPickFontColor = findViewById(R.id.fontColorPicker);
        btnPickFontColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        openColorPicker();
    }

    public void openColorPicker() {
        int defaultColor = ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary);
        AmbilWarnaDialog colorDialog = new AmbilWarnaDialog(this, defaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                LinearLayout layout = findViewById(R.id.layout);
                layout.setBackgroundColor(color);
            }
        });
        colorDialog.show();
    }
}