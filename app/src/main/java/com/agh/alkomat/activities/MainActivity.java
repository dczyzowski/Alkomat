package com.agh.alkomat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.agh.alkomat.R;
import com.agh.alkomat.adapters.GridAdapter;
import com.agh.alkomat.dialogs.AddDrinkDialog;
import com.agh.alkomat.models.DrinkModel;
import com.agh.alkomat.models.UserProfile;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static UserProfile user;
    GridView drinksGrids;
    GridAdapter adapter;
    static ArrayList<DrinkModel> drinks;


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
                Intent intent = new Intent(getBaseContext(), AddDrinkDialog.class);
                startActivity(intent);
            }
        });

        drinksGrids = (GridView) findViewById(R.id.drinks_grids);
        //moj uzytkownik , jego imie i waga
        user = new UserProfile("Ja", 65);
        drinks = new ArrayList<>();


        adapter = new GridAdapter(getBaseContext(), R.layout.grid_element, drinks);
        drinksGrids.setAdapter(adapter);

        timerHandler.postDelayed(timerRunnable, 0);

    }

    //licznik odswierza liste co sekunde
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {

            adapter.notifyDataSetChanged();

            timerHandler.postDelayed(this, 1000);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //dodaje nowy obiekt do listy
    public static void addNewDrink(String name, double volume, double quantity){
        if (drinks.isEmpty())
            drinks.add(new DrinkModel(name, volume, quantity, user.getWeight()));
        else{
            long timetorest = drinks.get(drinks.size()-1).getTimeToRest();
            drinks.add(new DrinkModel(name, volume, quantity, user.getWeight(),
                    timetorest));
        }
    }
}
