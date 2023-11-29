package com.example.projectgitm8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class RestaurantAdapter extends ArrayAdapter<Restaurants.Restaurant> {
    private final Activity context;
    private final Restaurants.Restaurant[] lrestaurants;

    public RestaurantAdapter(Activity context, Restaurants.Restaurant[] lrestaurants) {
        super(context, R.layout.restaurant_list_item, lrestaurants);

        this.context = context;
        this.lrestaurants = lrestaurants;
    }


    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View rowView = inflater.inflate(R.layout.restaurant_list_item, null,true);

        TextView titleText = rowView.findViewById(R.id.restaurantName);
        TextView subtitleText = rowView.findViewById(R.id.RestaurantAddress);

        titleText.setText(lrestaurants[position].getNom());
        subtitleText.setText(lrestaurants[position].getAdreca());

        return rowView;

    }
}
