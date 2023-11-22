package com.example.projectgitm8;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDetail extends AppCompatActivity {

    int[] lImatges = {
            R.drawable.hotel,
            R.drawable.hotel,
            R.drawable.hotel,
            R.drawable.business,
            R.drawable.business,
            R.drawable.business,
            R.drawable.parking,
            R.drawable.parking,
            R.drawable.parking,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.movie,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        Bundle sacInformacio = getIntent().getExtras();
        TextView hotelName = findViewById(R.id.nameHotel);
        assert sacInformacio != null;
        hotelName.setText(sacInformacio.getString("nom"));
        TextView hotelAddress = findViewById(R.id.hotelAddress);
        hotelAddress.setText(sacInformacio.getString("address"));
        TextView hotelWeb = findViewById(R.id.hotelWeb);
        hotelWeb.setText(sacInformacio.getString("web"));
        TextView hotelPhone = findViewById(R.id.hotelPhone);
        hotelPhone.setText(sacInformacio.getString("telefon"));
        int numImatge = sacInformacio.getInt("imatge");
        int categoria = sacInformacio.getInt("categoria");
        ImageView imatgeHotel = findViewById(R.id.imageHotel);
        imatgeHotel.setImageResource(lImatges[numImatge]);

        String categoryText = String.valueOf(categoria);
        if (categoria==1) categoryText += " estrella";
        else categoryText += " estrelles";
        TextView hotelCategory = findViewById(R.id.hotelCategory);
        hotelCategory.setText(categoryText);
    }
}