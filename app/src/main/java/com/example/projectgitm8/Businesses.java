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

public class Businesses extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3;
    ImageView iv1, iv2, iv3;
    ImageView ivwc1, ivwc2, ivwc3;
    Button btnTlf1, btnTlf2, btnTlf3;
    Button btnAdress1, btnAdress2, btnAdress3;
    Button btnWeb1, btnWeb2, btnWeb3;
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
        ivwc1 = findViewById(R.id.wc1);
        ivwc2 = findViewById(R.id.wc2);
        ivwc3 = findViewById(R.id.wc3);
        btnTlf1 = findViewById(R.id.businessBtnTlf1);
        btnTlf2 = findViewById(R.id.businessBtnTlf2);
        btnTlf3 = findViewById(R.id.businessBtnTlf3);
        btnTlf1.setOnClickListener(this);
        btnTlf2.setOnClickListener(this);
        btnTlf3.setOnClickListener(this);
        btnAdress1 = findViewById(R.id.businessAdress1);
        btnAdress2 = findViewById(R.id.businessAdress2);
        btnAdress3 = findViewById(R.id.businessAdress3);
        btnWeb1 = findViewById(R.id.businessWeb1);
        btnWeb2 = findViewById(R.id.businessWeb2);
        btnWeb3 = findViewById(R.id.businessWeb3);

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
        btnAdress3.setOnClickListener(v -> {
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

        btnWeb1.setOnClickListener(v -> {
            String url = "";
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                url = getString(R.string.webPerruqueria1);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                url = getString(R.string.webTaller1);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                url = getString(R.string.webBotiga1);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        btnWeb2.setOnClickListener(v -> {
            String url = "";
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                url = getString(R.string.webPerruqueria2);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                url = getString(R.string.webTaller2);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                url = getString(R.string.webBotiga2);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        btnWeb3.setOnClickListener(v -> {
            String url = "";
            if (spinner.getSelectedItem().equals("Perruqueria")) {
                url = getString(R.string.webPerruqueria3);
            } else if (spinner.getSelectedItem().equals("Taller")) {
                url = getString(R.string.webTaller3);
            } else if (spinner.getSelectedItem().equals("Botigues")) {
                url = getString(R.string.webBotiga3);
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });



        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (spinner.getSelectedItem().equals("Perruqueria")) {
            if (v == btnTlf1) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phonePerruqueria)[0]));
            } else if (v == btnTlf2) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phonePerruqueria)[1]));
            } else if (v == btnTlf3) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phonePerruqueria)[2]));
            }
        } else if (spinner.getSelectedItem().equals("Taller")) {
            if (v == btnTlf1) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneTaller)[0]));
            } else if (v == btnTlf2) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneTaller)[1]));
            } else if (v == btnTlf3) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneTaller)[2]));
            }
        } else if (spinner.getSelectedItem().equals("Botigues")) {
            if (v == btnTlf1) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneBotiga)[0]));
            } else if (v == btnTlf2) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneBotiga)[1]));
            } else if (v == btnTlf3) {
                intent.setData(Uri.parse("tel:" + getResources().getStringArray(R.array.phoneBotiga)[2]));
            }
        }

        startActivity(intent);
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
            netejaImatges();
            tv1.setText(getResources().getString(R.string.perruqueria1));
            tv2.setText(getResources().getString(R.string.perruqueria2));
            tv3.setText(getResources().getString(R.string.perruqueria3));
            iv1.setImageResource(R.drawable.buinessbellesaa);
            iv2.setImageResource(R.drawable.businesscarme);
            iv3.setImageResource(R.drawable.businesscarol);
            if (getString(R.string.wcp1).equals("true")) {
                ivwc1.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wcp2).equals("true")) {
                ivwc2.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wcp3).equals("true")) {
                ivwc3.setImageResource(R.drawable.wheelchair);
            }
            setTelefon();
        } else if (spinner.getSelectedItem().equals("Taller")) {
            netejaImatges();
            tv1.setText(getResources().getString(R.string.taller1));
            tv2.setText(getResources().getString(R.string.taller2));
            tv3.setText(getResources().getString(R.string.taller3));
            iv1.setImageResource(R.drawable.businessautorapid);
            iv2.setImageResource(R.drawable.businesseutrasa);
            iv3.setImageResource(R.drawable.businesstonico);
            if (getString(R.string.wct1).equals("true")) {
                ivwc1.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wct2).equals("true")) {
                ivwc2.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wct3).equals("true")) {
                ivwc3.setImageResource(R.drawable.wheelchair);
            }
            setTelefon();
        } else if (spinner.getSelectedItem().equals("Botigues")) {
            netejaImatges();
            tv1.setText(getResources().getString(R.string.botiga1));
            tv2.setText(getResources().getString(R.string.botiga2));
            tv3.setText(getResources().getString(R.string.botiga3));
            iv1.setImageResource(R.drawable.businessenigma);
            iv2.setImageResource(R.drawable.businessfinetwork);
            iv3.setImageResource(R.drawable.businesssesobarcelona);
            if (getString(R.string.wcb1).equals("true")) {
                ivwc1.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wcb2).equals("true")) {
                ivwc2.setImageResource(R.drawable.wheelchair);
            } else if (getString(R.string.wcb3).equals("true")) {
                ivwc3.setImageResource(R.drawable.wheelchair);
            }
            setTelefon();
        }
    }

    public void netejaImatges() {
        ivwc1.setImageResource(0);
        ivwc2.setImageResource(0);
        ivwc3.setImageResource(0);
    }

    @SuppressLint("SetTextI18n")
    public void setTelefon() {
        if (spinner.getSelectedItem().equals("Perruqueria")) {
            btnTlf1.setText(getResources().getStringArray(R.array.phonePerruqueria)[0].toString());
            btnTlf2.setText(getResources().getStringArray(R.array.phonePerruqueria)[1].toString());
            btnTlf3.setText(getResources().getStringArray(R.array.phonePerruqueria)[2].toString());
        } else if (spinner.getSelectedItem().equals("Taller")) {
            btnTlf1.setText(getResources().getStringArray(R.array.phoneTaller)[0].toString());
            btnTlf2.setText(getResources().getStringArray(R.array.phoneTaller)[1].toString());
            btnTlf3.setText(getResources().getStringArray(R.array.phoneTaller)[2].toString());
        } else if (spinner.getSelectedItem().equals("Botigues")) {
            btnTlf1.setText(getResources().getStringArray(R.array.phoneBotiga)[0].toString());
            btnTlf2.setText(getResources().getStringArray(R.array.phoneBotiga)[0].toString());
            btnTlf3.setText(getResources().getStringArray(R.array.phoneBotiga)[0].toString());
        }
    }
}