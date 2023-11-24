package com.example.projectgitm8;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private final Activity context;
    private final Hotel[] lHotels;
    public HotelAdapter(Activity context, Hotel[] lHotels) {
        super(context, R.layout.hotel_list_item, lHotels);
        this.context = context;
        this.lHotels = lHotels;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View rowView = inflater.inflate(R.layout.hotel_list_item, null,true);

        TextView titleText = rowView.findViewById(R.id.hotelName);
        TextView subtitleText = rowView.findViewById(R.id.hotelAddress);
        titleText.setText(lHotels[position].getNom());
        subtitleText.setText(lHotels[position].getAdreca());

        return rowView;

    }
}
