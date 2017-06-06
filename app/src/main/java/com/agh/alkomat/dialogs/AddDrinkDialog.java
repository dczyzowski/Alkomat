package com.agh.alkomat.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import com.agh.alkomat.R;
import com.agh.alkomat.activities.MainActivity;

import java.util.ArrayList;

public class AddDrinkDialog extends AppCompatActivity {

    Spinner drinksSpinner;
    NumberPicker voltagePicker;
    NumberPicker quantityPicker;

    ArrayList<String> drinki;

    double[] voltageValues;
    double[] quantityValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_drink);

        drinksSpinner = (Spinner) findViewById(R.id.get_drink_spinner);
        voltagePicker = (NumberPicker) findViewById(R.id.zawartosc_picker);
        quantityPicker = (NumberPicker) findViewById(R.id.pojemnosc_picker);

        drinki = new ArrayList<>();
        String[] voltageOptions = new String[31];
        String[] quantityOptions = new String[31];

        voltageValues = new double[31];
        quantityValues = new double[31];

        drinki.add("Mojito");
        drinki.add("Wodka");
        drinki.add("Whisky");
        drinki.add("Piwo");
        drinki.add("Cherbata z prądem");

        drinksSpinner.setAdapter(new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, drinki));

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


    public void addNewDrink(View view) {
        MainActivity.addNewDrink(drinksSpinner.getSelectedItem().toString(),
                voltageValues[voltagePicker.getValue()], quantityValues[quantityPicker.getValue()]);

        finish();
    }
}
