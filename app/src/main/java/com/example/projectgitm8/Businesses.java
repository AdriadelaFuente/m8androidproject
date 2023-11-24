package com.example.projectgitm8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class Businesses extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    ImageView iv1, iv2, iv3;
    Button btnTlf1, btnTlf2, btnTlf3;
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

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                tv1.setText("BELLESA ESSENCIAL");
                tv2.setText("CARME ESTILISTES");
                tv3.setText("CAROL BRUGUERA");
            }
        });
    }

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