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

            int posicioHotelClicat = position;
            if (categoriaTriada>0) posicioHotelClicat += (categoriaTriada - 1)*3;
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
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getHotels()));

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
        //data.clear();

        data[12] = new Hotel("Hotel Montmeló", "C/Nou,1 Montmeló", "www.hotelmontmelo.com", "935722424", 12, 1);
        data[13] = new Hotel("Hotel Cim Vallès", "Carrer Calderi, 1, 08130 Santa Perpètua de Mogoda, Barcelona", "www.hotelcimvalles.com/", "932 41 87 11", 13, 1);
        data[14] = new Hotel("Hotel HC Mollet Barcelona", "Carrer de Can Flequer, 30, 08100 Mollet del Vallès, Barcelona", "www.hotelhc.es/", "935 70 64 34", 14, 1);

        data[4] = new Hotel("B&B Hotel Barcelona Granollers", "Carrer Valldoriolf, 1, 08520 Granollers, Barcelona", "www.hotel-bb.com", "938 40 71 32", 4, 2);
        data[6] = new Hotel("ibis Barcelona Montmelo Granollers", "Poligono Industrial, C. Can Gordi 1 Pol. Ind, Carrer de Can Catala, 08403 Granollers, Barcelona", "No disponible", "935 68 90 60", 6, 2);
        data[8] = new Hotel("Porta de Gallecs", "Carrer de Gallecs, 68, 08100 Mollet del Vallès, Barcelona", "www.hotelportadegallecs.com", "935 79 58 00", 8, 2);

        data[3] = new Hotel("Hotel Ciutat de Granollers", "Carrer Turo d'en Bruguet 2, 08402 Granollers, Barcelona", "www.hotelciutatgranollers.com/", "938 79 62 20", 3, 3);
        data[5] = new Hotel("Hotel H", "Carretera del Masnou, Km.14, 08402 Granollers, Barcelona", "www.hotelh.es", "938 60 66 88", 5, 3);
        data[7] = new Hotel("Hotel Iris Granollers", "Av. Sant Esteve, 92, 08402 Granollers, Barcelona", "www.hoteliris.com/", "938 79 29 29", 7, 3);

        data[0] = new Hotel("CASA FONDA EUROPA", "Carrer d'Agustí Viñamata, 2, 08402 Granollers, Barcelona", "www.fondaeuropa.eu", "938 70 03 12", 0, 4);
        data[1] = new Hotel("HOTEL AUGUSTA BARCELONA VALLÈS", "AP-7, Km 12 5, 08410, Barcelona", "www.hotelaugustavalles.com", "938 45 60 50", 1, 4);
        data[2] = new Hotel("Aparthotel Atenea Valles", "Magallanes/Aragón, s/n, 08401 Granollers, Barcelona", "www.aparthotelateneavalles.com/es/", "938 79 48 20", 2, 4);

        data[9] = new Hotel("Balneari Blancafort", "Carrer de la Mina, 7, 08530 La Garriga, Barcelona", "www.hotelblancafort.com/", "938 60 56 00", 9, 5);
        data[10] = new Hotel("Hotel Balneari Termes Victoria", "Carrer de Barcelona, 12, 08140 Caldes de Montbui, Barcelona", "www.termesvictoria.com/", "938 65 01 50", 10, 5);
        data[11] = new Hotel("Hotel Parets", "Avda. Cataluña, 2 08150 Paret del valles. - Barcelona", "www.hotelparets.com/", "663 681 253", 11, 5);

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