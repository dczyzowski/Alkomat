package com.agh.alkomat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.agh.alkomat.models.DrinkModel;

import java.util.ArrayList;

public class AddDrinkActivity extends AppCompatActivity {

    Spinner drinksSpinner;
    NumberPicker voltagePicker;
    NumberPicker quantityPicker;

    ArrayList<String> drinki;

    int[] voltageValues;
    int[] quantityValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_drink);

        //deklaruje obiekty
        drinksSpinner = (Spinner) findViewById(R.id.get_drink_spinner);
        voltagePicker = (NumberPicker) findViewById(R.id.zawartosc_picker);
        quantityPicker = (NumberPicker) findViewById(R.id.pojemnosc_picker);

        // deklaruje liste i tablice
        drinki = new ArrayList<>();
        String[] voltageOptions = new String[31];
        String[] quantityOptions = new String[31];
        voltageValues = new int[31];
        quantityValues = new int[31];

        drinki.add("Gin");
        drinki.add("Wodka");
        drinki.add("Whisky");
        drinki.add("Piwo");
        drinki.add("Rum");
        drinki.add("Wino");

        // adapter adaptuje liste do widoku w spinnerze
        drinksSpinner.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, drinki));

        // wypelnie tablic wartosciami
        for(int i = 0; i <= 30 ; i++){
            voltageOptions[i] = i*2 + "%";
            voltageValues[i] = i*2;
            quantityOptions[i] = i*25 + "ml";
            quantityValues[i] = i*25;
        }

        quantityPicker.setMaxValue(30);
        voltagePicker.setMaxValue(30);

        quantityPicker.setDisplayedValues(quantityOptions);
        voltagePicker.setDisplayedValues(voltageOptions);
        }

    // metoda wywoływana po nacisnieciu przycisku zapisz
    public void addNewDrink(View view) {
        MainActivity.drinksList.add(new DrinkModel(drinksSpinner.getSelectedItem().toString(),
                voltageValues[voltagePicker.getValue()], quantityValues[quantityPicker.getValue()]));

        finish();
    }
}
