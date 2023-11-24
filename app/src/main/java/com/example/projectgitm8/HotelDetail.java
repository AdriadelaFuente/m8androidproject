package com.example.projectgitm8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDetail extends AppCompatActivity implements View.OnClickListener {
    LinearLayout web, phone;

    String webUrl, phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_detail);

        web = findViewById(R.id.webLink);
        web.setOnClickListener(this);

        phone = findViewById(R.id.phoneNumber);
        phone.setOnClickListener(this);

        Bundle sacInformacio = getIntent().getExtras();
        TextView hotelName = findViewById(R.id.nameHotel);
        assert sacInformacio != null;
        hotelName.setText(sacInformacio.getString("nom"));
        TextView hotelAddress = findViewById(R.id.hotelAddress);
        hotelAddress.setText(sacInformacio.getString("address"));
        TextView hotelWeb = findViewById(R.id.hotelWeb);
        webUrl = sacInformacio.getString("web");
        hotelWeb.setText(webUrl);
        TextView hotelPhone = findViewById(R.id.hotelPhone);
        phoneNum = sacInformacio.getString("telefon");
        hotelPhone.setText(phoneNum);
        int numImatge = sacInformacio.getInt("imatge");
        int categoria = sacInformacio.getInt("categoria");
        ImageView imatgeHotel = findViewById(R.id.imageHotel);
        imatgeHotel.setImageResource(numImatge);

        String categoryText = String.valueOf(categoria);
        if (categoria==1) categoryText += " estrella";
        else categoryText += " estrelles";
        TextView hotelCategory = findViewById(R.id.hotelCategory);
        hotelCategory.setText(categoryText);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v==web) {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://" + webUrl));
        }
        else if (v==phone) {
            intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNum));
        }
        startActivity(intent);
    }
}