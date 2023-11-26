package com.example.projectgitm8;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Businesses extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    ImageView iv1, iv2, iv3;
    Button btnTlf1, btnTlf2, btnTlf3;
    Button btnAdress1, btnAdress2, btnAdress3;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businesses);

        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        iv1 = findViewById(R.id.businessImg1);
        iv2 = findViewById(R.id.businessImg2);
        iv3 = findViewById(R.id.businessImg3);
        btnTlf1 = findViewById(R.id.businessBtnTlf1);
        btnTlf2 = findViewById(R.id.businessBtnTlf2);
        btnTlf3 = findViewById(R.id.businessBtnTlf3);
        btnAdress1 = findViewById(R.id.businessAdress1);
        btnAdress2 = findViewById(R.id.businessAdress2);
        btnAdress3 = findViewById(R.id.businessAdress3);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.spinner_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setContentBusiness();
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                tv1.setText("BELLESA ESSENCIAL");
                tv2.setText("CARME ESTILISTES");
                tv3.setText("CAROL BRUGUERA");
            }
        });





        btnAdress1.setOnClickListener(v -> {
            String location = "";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                location = getString(R.string.businessAdressPerruqueria1);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                location = getString(R.string.businessAdressTaller1);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                location = getString(R.string.businessAdressBotiga1);
            }
            intent.setData(Uri.parse("geo:0,0?q=" + location));
            Intent chooser = Intent.createChooser(intent, "Launch Maps");
            startActivity(chooser);
        });
        btnAdress2.setOnClickListener(v -> {
            String location = "";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                location = getString(R.string.businessAdressPerruqueria2);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                location = getString(R.string.businessAdressTaller2);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                location = getString(R.string.businessAdressBotiga2);
            }
            intent.setData(Uri.parse("geo:0,0?q=" + location));
            Intent chooser = Intent.createChooser(intent, "Launch Maps");
            startActivity(chooser);
        });
        btnAdress1.setOnClickListener(v -> {
            String location = "";
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                location = getString(R.string.businessAdressPerruqueria3);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                location = getString(R.string.businessAdressTaller3);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                location = getString(R.string.businessAdressBotiga3);
            }
            intent.setData(Uri.parse("geo:0,0?q=" + location));
            Intent chooser = Intent.createChooser(intent, "Launch Maps");
            startActivity(chooser);
        });

        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.businessLayout);
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

    @SuppressLint("SetTextI18n")
    public void setContentBusiness() {
        if (spinner.getSelectedItem().equals("Perruqueria")) {
            tv1.setText("BELLESA ESSENCIAL");
            tv2.setText("CARME ESTILISTES");
            tv3.setText("CAROL BRUGUERA");
            iv1.setImageResource(R.drawable.buinessbellesaa);
            iv2.setImageResource(R.drawable.businesscarme);
            iv3.setImageResource(R.drawable.businesscarol);
            setTelefon();
        } else if (spinner.getSelectedItem().equals("Taller")) {
            tv1.setText("AUTORAPID MACOA");
            tv2.setText("EUTRASA-PEUGEOT");
            tv3.setText("PNEUMATICS TONICO");
            iv1.setImageResource(R.drawable.businesseutrasa);
            iv2.setImageResource(R.drawable.businessautorapid);
            iv3.setImageResource(R.drawable.businesstonico);
            setTelefon();
        } else if (spinner.getSelectedItem().equals("Botigues")) {
            tv1.setText("EMAGINA");
            tv2.setText("FINETWORK GRANOLLERS");
            tv3.setText("SEO.BARCELONA");
            iv1.setImageResource(R.drawable.businessenigma);
            iv2.setImageResource(R.drawable.businessfinetwork);
            iv3.setImageResource(R.drawable.businesssesobarcelona);
            setTelefon();
        }
    }

    @SuppressLint("SetTextI18n")
    public void setTelefon() {
        if (spinner.getSelectedItem().equals("Perruqueria")) {
            btnTlf1.setText("938708807");
            btnTlf2.setText("938706497");
            btnTlf3.setText("938794016");
        } else if (spinner.getSelectedItem().equals("Taller")) {
            btnTlf1.setText("938790296");
            btnTlf2.setText("938494100");
            btnTlf3.setText("938793553");
        } else if (spinner.getSelectedItem().equals("Botigues")) {
            btnTlf1.setText("900828181");
            btnTlf2.setText("615260127");
            btnTlf3.setText("931303108/ 695486554");
        }
    }
}