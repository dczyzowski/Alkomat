package com.agh.alkomat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.agh.alkomat.adapters.GridAdapter;
import com.agh.alkomat.models.DrinkModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView drinksGrids;
    GridAdapter adapter;
    static ArrayList<DrinkModel> drinksList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), AddDrinkActivity.class);
                startActivity(intent);
            }
        });

        drinksGrids = (GridView) findViewById(R.id.drinks_grids);
        drinksList = new ArrayList<>();
        adapter = new GridAdapter(getBaseContext(), R.layout.grid_element, drinksList);
        drinksGrids.setAdapter(adapter);

        //startuje licznik
        timerHandler.postDelayed(timerRunnable, 0);
    }

    //licznik odswierza liste co sekunde
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            // co sekunde zmniejsza czas kazdego obiektu w liscie z drinkami wypitymi
            adapter.notifyDataSetChanged();
            for(DrinkModel drink : drinksList){
                drink.setTimeToRest(drink.getTimeToRest() - 1);
            }

            timerHandler.postDelayed(this, 1000);
        }
    };
}
