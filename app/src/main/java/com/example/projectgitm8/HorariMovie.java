package com.example.projectgitm8;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HorariMovie extends AppCompatActivity {

    TextView tvDilluns, tvDimarts, tvDimecres, tvDijous, tvDivendres, tvDissabte, tvDiumenge;
    TextView tvMovieName;
    String name = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_horari);

        Bundle huevo = getIntent().getExtras();
        name = huevo.getString("type");
        tvMovieName = findViewById(R.id.NomMovieHorari);
        tvMovieName.setText(tvMovieName.getText() + name);
        tvDilluns = findViewById(R.id.tvDilluns);
        tvDimarts = findViewById(R.id.tvDimarts);
        tvDimecres = findViewById(R.id.tvDimecres);
        tvDijous = findViewById(R.id.tvDijous);
        tvDivendres = findViewById(R.id.tvDivendres);
        tvDissabte = findViewById(R.id.tvDissabte);
        tvDiumenge = findViewById(R.id.tvDiumenge);

        setHorari();


        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.HorariMovieLayout);
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

    public void setHorari() {
        if (name.equals("Five Nights At Freddy's")) {
            String [] _arrays = getResources().getStringArray(R.array.horarisFnaf);
            tvDilluns.setText(_arrays[0].toString());
            tvDimarts.setText(_arrays[1].toString());
            tvDimecres.setText(_arrays[2].toString());
            tvDijous.setText(_arrays[3].toString());
            tvDivendres.setText(_arrays[4].toString());
            tvDissabte.setText(_arrays[5].toString());
            tvDiumenge.setText(_arrays[6].toString());
        } else if (name.equals("Los Juegos del Hambre")) {
            String [] _arrays = getResources().getStringArray(R.array.horarisJuegos);
            tvDilluns.setText(_arrays[0].toString());
            tvDimarts.setText(_arrays[1].toString());
            tvDimecres.setText(_arrays[2].toString());
            tvDijous.setText(_arrays[3].toString());
            tvDivendres.setText(_arrays[4].toString());
            tvDissabte.setText(_arrays[5].toString());
            tvDiumenge.setText(_arrays[6].toString());
        } else if (name.equals("Hypnotic")) {
            String [] _arrays = getResources().getStringArray(R.array.horarisHypnotic);
            tvDilluns.setText(_arrays[0].toString());
            tvDimarts.setText(_arrays[1].toString());
            tvDimecres.setText(_arrays[2].toString());
            tvDijous.setText(_arrays[3].toString());
            tvDivendres.setText(_arrays[4].toString());
            tvDissabte.setText(_arrays[5].toString());
            tvDiumenge.setText(_arrays[6].toString());
        } else if (name.equals("The Marvel")) {
            String [] _arrays = getResources().getStringArray(R.array.horarisMarvel);
            tvDilluns.setText(_arrays[0].toString());
            tvDimarts.setText(_arrays[1].toString());
            tvDimecres.setText(_arrays[2].toString());
            tvDijous.setText(_arrays[3].toString());
            tvDivendres.setText(_arrays[4].toString());
            tvDissabte.setText(_arrays[5].toString());
            tvDiumenge.setText(_arrays[6].toString());
        } if (name.equals("La Patrulla Canina")) {
            String [] _arrays = getResources().getStringArray(R.array.horarisPatrulla);
            tvDilluns.setText(_arrays[0].toString());
            tvDimarts.setText(_arrays[1].toString());
            tvDimecres.setText(_arrays[2].toString());
            tvDijous.setText(_arrays[3].toString());
            tvDivendres.setText(_arrays[4].toString());
            tvDissabte.setText(_arrays[5].toString());
            tvDiumenge.setText(_arrays[6].toString());
        }
    }
}