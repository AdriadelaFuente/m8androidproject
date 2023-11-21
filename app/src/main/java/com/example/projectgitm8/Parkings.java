package com.example.projectgitm8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;

//class Parking {
//    public String name;
//    public int places;
//}

public class Parkings extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parkings);


//        Parking act = new Parking();
//        Parking[] items = new Parking[3];
//        act.name = "Parking #1"; act.places = 251;
//        items[0] = act;
//        act.name = "Parking #2"; act.places = 189;
//        items[1] = act;
//        act.name = "Parking #3"; act.places = 225;
//        items[2] = act;
        CardView cardParking1, cardParking2, cardParking3, cardParking4;
        cardParking1 = findViewById(R.id.parking1);
        cardParking1.setOnClickListener(this);

        cardParking2 = findViewById(R.id.parking2);
        cardParking2.setOnClickListener(this);

        cardParking3 = findViewById(R.id.parking3);
        cardParking3.setOnClickListener(this);

        cardParking4 = findViewById(R.id.parking4);
        cardParking4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "CardView clicat!", Toast.LENGTH_SHORT).show();
    }
}