package com.example.projectgitm8;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.projectgitm8.databinding.ActivityParkingDetailBinding;

public class ParkingDetail extends FragmentActivity implements OnMapReadyCallback {
    private String parkingName;
    private float parkingLongX;
    private float parkingLongY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle sacInformacio = getIntent().getExtras();
        assert sacInformacio != null;
        parkingName = sacInformacio.getString("name");
        parkingLongX = sacInformacio.getFloat("longX");
        parkingLongY = sacInformacio.getFloat("longY");

        com.example.projectgitm8.databinding.ActivityParkingDetailBinding binding = ActivityParkingDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng ubicacioParking = new LatLng(parkingLongX, parkingLongY);
        googleMap.addMarker(new MarkerOptions().position(ubicacioParking).title(parkingName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacioParking));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(17));
    }
}