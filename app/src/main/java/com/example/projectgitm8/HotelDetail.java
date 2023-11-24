package com.example.projectgitm8;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HotelDetail extends AppCompatActivity implements View.OnClickListener {
    ImageView web, phone;

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
        //TextView hotelWeb = findViewById(R.id.hotelWeb);
        webUrl = sacInformacio.getString("web");
        //hotelWeb.setText(webUrl);
        //TextView hotelPhone = findViewById(R.id.hotelPhone);
        phoneNum = sacInformacio.getString("telefon");
        //hotelPhone.setText(phoneNum);
        int numImatge = sacInformacio.getInt("imatge");
        ImageView imatgeHotel = findViewById(R.id.imageHotel);
        imatgeHotel.setImageResource(numImatge);

        int categoria = sacInformacio.getInt("categoria");
        assignHotelStarsImage(categoria);

//        String categoryText = String.valueOf(categoria);
//        if (categoria==1) categoryText += " estrella";
//        else categoryText += " estrelles";
//        TextView hotelCategory = findViewById(R.id.hotelCategory);
//        hotelCategory.setText(categoryText);
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

    private void assignHotelStarsImage(int numStars) {
        ImageView hotelStarsImage = findViewById(R.id.hotelStars);
        int imgId = R.drawable.stars_1;
        if (numStars==2) imgId = R.drawable.stars_2;
        else if (numStars==3) imgId = R.drawable.stars_3;
        else if (numStars==4) imgId = R.drawable.stars_4;
        else if (numStars==5) imgId = R.drawable.stars_5;
        hotelStarsImage.setImageResource(imgId);
    }
}