package com.example.projectgitm8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Restaurants extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    ListView listView;
    Spinner spinner;
    RestaurantAdapter adapter;
    String[] categories = {"Tots", "Italià", "Japonès", "Vegetaria", "Marisc", "Hamburgueseria"};

    private int categoriaTriada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);

        spinner = findViewById(R.id.rSpinner);
        spinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories));

        listView = findViewById(R.id.restaurantList);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Restaurant[] lRestaurants = getRestaurant();

            int posicioRestaurantClicat = position + (categoriaTriada-1)*3;
            if (categoriaTriada==0) posicioRestaurantClicat = position;
            Restaurant restaurantTriat = lRestaurants[posicioRestaurantClicat];

            Intent intent = new Intent(Restaurants.this, RestaurantDetail.class);
            Bundle sacInformacio = new Bundle();
            sacInformacio.putString("nom", restaurantTriat.getNom());
            sacInformacio.putString("address", restaurantTriat.getAdreca());
            sacInformacio.putString("web", restaurantTriat.getWeb());
            sacInformacio.putString("telefon", restaurantTriat.getTelefon());
            sacInformacio.putInt("imatge", restaurantTriat.getNumImatge());
            sacInformacio.putInt("categoria", restaurantTriat.getCategory());
            intent.putExtras(sacInformacio);
            startActivity(intent);


        });
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getRestaurant()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categoriaTriada = position;
                if (position >= 0 && position <= categories.length)
                    getRestaurantsByCategory(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        EventBus.getDefault().register(this);
        loadInitialColor();
    }

    private void loadInitialColor() {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("app_settings", Context.MODE_PRIVATE);
        LinearLayout linearLayout = findViewById(R.id.restaurantsLayout);
        linearLayout.setBackgroundColor(sharedPref.getInt("bgColor", Color.WHITE));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackgroundColorChanged(BackgroundColorChangedEvent event) {
        int newColor = event.getNewColor();
        View layout = findViewById(R.id.mainLayout);
        layout.setBackgroundColor(newColor);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private Restaurant[] getRestaurant() {
        Restaurant[] data = new Restaurant[15];
        //data.clear();

        data[0] = new Restaurant("Il Sapore Italiano", " Carrer de Ponent, 41, 08401 Granollers, Barcelona", "www.ilsaporeitalianogranollers.es", "938 40 15 32", 12, 1);
        data[1] = new Restaurant("Il Cenacolo", "Carrer de Josep Umbert i Ventura, 109, 08402 Granollers, Barcelona", "www.ilcenacologranollers.com/", "938 70 37 81", 13, 1);
        data[2] = new Restaurant("Restaurant La Tagliatella", "Centre Comercial El Nord", "www.latagliatella.es", "938 46 75 05", 14, 1);

        data[3] = new Restaurant("Yao Sushi", "Carrer Príncep de Viana, 8, 08401 Granollers, Barcelona", "www.yaosuchigranollers.com", "938 79 11 23", 4, 2);
        data[4] = new Restaurant("Wagaya Restaurant", "Carrer de Girona, 32, 08402 Granollers, Barcelona", "www.wayaga.es", "931 58 34 81", 6, 2);
        data[5] = new Restaurant("Restaurant Atarasii", "Carrer de Joan Prim, 4, 08402 Granollers, Barcelona", "www.atarasiisushi.es", "689 87 56 85", 8, 2);

        data[6] = new Restaurant("La Cookessa Bio Granollers", "Carrer de Ponent, 25, 08401 Granollers, Barcelona", "www.lacookessabio.com", "931 35 21 36", 3, 3);
        data[7] = new Restaurant("Ànima de Granollers", "Carrer de Girona, 4, 08402 Granollers, Barcelona", "No disponible", "938 70 42 91", 5, 3);
        data[8] = new Restaurant("Restaurant El Gato Verde", "Carrer de Josep Torras I Bages, 50, 08401 Granollers, Barcelona", "No disponible", "938 40 38 36", 7, 3);

        data[9] = new Restaurant("Restaurante Marisquería Os Galegos", "Travessia del Lledoner, 9, 08402 Granollers, Barcelona", "www.osgalegos.com", "938 40 30 39", 0, 4);
        data[10] = new Restaurant("Can Pipa", "Carrer Aureli Font, 5, Granollers, Cataluña, España", "No disponible", " 938 79 47 69", 1, 4);
        data[11] = new Restaurant("Restaurant La Gamba", "Carrer de Santa Elisabet, 6, 08401 Granollers, Barcelona", "www.lagamba.com", "938 79 19 87", 2, 4);

        data[12] = new Restaurant("Burger life", "Carrer Primer Marquès Franqueses, 73, 08402 Granollers, Barcelona", "www.burgerlife73.es", "938 79 19 87", 9, 5);
        data[13] = new Restaurant("La Camperia", "Plaça de Pau Casals, 14, 08402 Granollers, Barcelona", "www.lacamperiagranollers.com", "673 10 99 69", 10, 5);
        data[14] = new Restaurant("La Tremenda", "Carrer de Girona, 22, 08402 Granollers, Barcelona", "www.latremenda.com", " 931 72 02 84", 11, 5);

        return data;
    }

    private void getRestaurantsByCategory(int categoria) {
        Restaurant[] lrestaurants = getRestaurant();

        if (categoria == 0) adapter = new RestaurantAdapter(this, lrestaurants);
        else {
            Restaurant[] filteredRestaurants = new Restaurant[3];
            int actRestaurant = 0;
            for (Restaurant restaurant : lrestaurants) {
                if (restaurant.getCategory() == categoria) {
                    filteredRestaurants[actRestaurant] = restaurant;
                    ++actRestaurant;
                }
            }
            adapter = new RestaurantAdapter(this, filteredRestaurants);
        }
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    static class Restaurant {
        private final String nom;
        private final String adreca;
        private final String web;
        private final String telefon;
        private final int numImatge;
        private final int categoria;

        public Restaurant(
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
}