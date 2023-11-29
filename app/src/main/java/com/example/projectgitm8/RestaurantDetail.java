package com.example.projectgitm8;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDetail extends AppCompatActivity {

    int[] lImatges = {
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant,
            R.drawable.restaurant
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Bundle sacInformacio = getIntent().getExtras();
        TextView restaurantName = findViewById(R.id.nameRestaurant);
        assert sacInformacio != null;
        restaurantName.setText(sacInformacio.getString("nom"));
        TextView restaurantAddress = findViewById(R.id.restaurantAddress);
        restaurantAddress.setText(sacInformacio.getString("address"));
        TextView restaurantWeb = findViewById(R.id.restaurantWeb);
        restaurantWeb.setText(sacInformacio.getString("web"));
        TextView restaurantPhone = findViewById(R.id.restaurantPhone);
        restaurantPhone.setText(sacInformacio.getString("telefon"));
        int numImatge = sacInformacio.getInt("imatge");
        int categoria = sacInformacio.getInt("categoria");
        ImageView imatgeRestaurant = findViewById(R.id.imageRestaurant);
        imatgeRestaurant.setImageResource(lImatges[numImatge]);

        String categoryText = String.valueOf(categoria);
        if (categoria==1) categoryText += " estrella";
        else categoryText += " estrelles";
        TextView restaurantCategory = findViewById(R.id.restaurantCategory);
        restaurantCategory.setText(categoryText);
    }
}