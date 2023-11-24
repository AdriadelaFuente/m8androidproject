package com.example.projectgitm8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class Hotels extends AppCompatActivity {

    ListView listView;
    Spinner spinner;
    HotelAdapter adapter;
    String[] categories = {"Tots", "1 estrella", "2 estrelles", "3 estrelles", "4 estrelles", "5 estrelles"};

    private int categoriaTriada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);

        spinner = findViewById(R.id.categories);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));

        listView = findViewById(R.id.hotelList);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Hotel[] lHotels = getHotels();

            int posicioHotelClicat = position + (categoriaTriada-1)*3;
            if (categoriaTriada==0) posicioHotelClicat = position;
            Hotel hotelTriat = lHotels[posicioHotelClicat];

            Intent intent = new Intent(Hotels.this, HotelDetail.class);
            Bundle sacInformacio = new Bundle();
            sacInformacio.putString("nom", hotelTriat.getNom());
            sacInformacio.putString("address", hotelTriat.getAdreca());
            sacInformacio.putString("web", hotelTriat.getWeb());
            sacInformacio.putString("telefon", hotelTriat.getTelefon());
            sacInformacio.putInt("imatge", hotelTriat.getNumImatge());
            sacInformacio.putInt("categoria", hotelTriat.getCategory());
            intent.putExtras(sacInformacio);
            startActivity(intent);
        });
        listView.setAdapter(new HotelAdapter(this, getHotels()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaTriada = position;
                if (position>=0 && position<=categories.length) getHotelsByCategory(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private Hotel[] getHotels() {
        Hotel[] data = new Hotel[15];

        data[0] = new Hotel("Hotel Montmeló", "C/Nou, 1 - Montmeló", "www.hotelmontmelo.com", "935 72 24 24", R.drawable.hotel_montmelo, 1);
        data[1] = new Hotel("Hotel Cim Vallès", "Carrer Calderi, 1 - Santa Perpètua de Mogoda", "www.hotelcimvalles.com/", "932 41 87 11", R.drawable.cim_valles, 1);
        data[2] = new Hotel("Hotel HC Mollet Barcelona", "Carrer de Can Flequer, 30 - Mollet del Vallès", "www.hotelhc.es/", "935 70 64 34", R.drawable.hc_mollet, 1);

        data[3] = new Hotel("B&B Hotel Barcelona Granollers", "Carrer Valldoriolf, 1 - Granollers", "www.hotel-bb.com", "938 40 71 32", R.drawable.b_and_b, 2);
        data[4] = new Hotel("ibis Barcelona Montmelo Granollers", "Pol. Ind. C. Can Gordi, 1 - Granollers", "www.all.accor.com", "935 68 90 60", R.drawable.ibis_montmelo, 2);
        data[5] = new Hotel("Porta de Gallecs", "Carrer de Gallecs, 68 - Mollet del Vallès", "www.hotelportadegallecs.com", "935 79 58 00", R.drawable.gallecs, 2);

        data[6] = new Hotel("Hotel Ciutat de Granollers", "Carrer Turo d'en Bruguet, 2 - Granollers", "www.hotelciutatgranollers.com/", "938 79 62 20", R.drawable.ciutat_granollers, 3);
        data[7] = new Hotel("Hotel H", "Carretera del Masnou, Km.14 - Granollers", "www.hotelh.es", "938 60 66 88", R.drawable.hotelh, 3);
        data[8] = new Hotel("Hotel Iris Granollers", "Av. Sant Esteve, 92 - Granollers", "www.hoteliris.com/", "938 79 29 29", R.drawable.iris_granollers, 3);

        data[9] = new Hotel("CASA FONDA EUROPA", "Carrer d'Agustí Viñamata, 2 - Granollers", "www.fondaeuropa.eu", "938 70 03 12", R.drawable.fonda_europa, 4);
        data[10] = new Hotel("HOTEL AUGUSTA BARCELONA VALLÈS", "AP-7, Km 12 5 - Barcelona", "www.hotelaugustavalles.com", "938 45 60 50", R.drawable.augusta_valles, 4);
        data[11] = new Hotel("Aparthotel Atenea Valles", "Magallanes/Aragón, s/n - Granollers", "www.aparthotelateneavalles.com/es/", "938 79 48 20", R.drawable.atenea_valles, 4);

        data[12] = new Hotel("Balneari Blancafort", "Carrer de la Mina, 7 - La Garriga", "www.hotelblancafort.com/", "938 60 56 00", R.drawable.blancafort, 5);
        data[13] = new Hotel("Hotel Balneari Termes Victoria", "Carrer de Barcelona, 12 - Caldes de Montbui", "www.termesvictoria.com/", "938 65 01 50", R.drawable.termes_victoria, 5);
        data[14] = new Hotel("Hotel Parets", "Avda. Cataluña, 2 - Paret del Vallès", "www.hotelparets.com/", "663 681 253", R.drawable.hotel_parets, 5);

        return data;
    }

    private void getHotelsByCategory(int categoria) {
        Hotel[] lHotels = getHotels();

        if (categoria==0) adapter = new HotelAdapter(this, lHotels);
        else {
            Hotel[] filteredHotels = new Hotel[3];
            int actHotel = 0;
            for (Hotel hotel : lHotels) {
                if (hotel.getCategory()==categoria) {
                    filteredHotels[actHotel] = hotel;
                    ++actHotel;
                }
            }
            adapter = new HotelAdapter(this, filteredHotels);
        }
        listView.setAdapter(adapter);
    }
}

class Hotel {
    private final String nom;
    private final String adreca;
    private final String web;
    private final String telefon;
    private final int numImatge;
    private final int categoria;

    public Hotel(
            String nom,
            String adreca,
            String web,
            String telefon,
            int numImatge,
            int categoria
    ) {
        this.nom = nom;
        this.adreca = adreca;
        this.web = web;
        this.telefon = telefon;
        this.numImatge = numImatge;
        this.categoria = categoria;
    }

    public int getCategory() {
        return categoria;
    }

    public String getNom() {
        return nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public int getNumImatge() {
        return numImatge;
    }

    public String getWeb() {
        return web;
    }

    public String getTelefon() {
        return telefon;
    }
}