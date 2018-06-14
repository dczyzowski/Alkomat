package com.agh.alkomat.models;

import android.os.CountDownTimer;

import java.util.Calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Damian on 2017-06-05.
 */

public class DrinkModel {

    String mDrinkName;

    long timeToRest;
    int mVolume;
    int mQuantity;
    double actAlcInBody;

    public DrinkModel(String drinkName, int volume, int quantity){
        mDrinkName = drinkName;
        mVolume = volume;
        mQuantity = quantity;

        // masa alkoholu = ilosc wypitego napoju w ml * zawartosc procentowa * 0.08 waga
        double pureAlc = mQuantity*mVolume*0.1*.8;

        // zawartosc alkoholu we krwi = ilosc spozytego alkoholu/ (wspolczynnik * waga osoby)
        double alcInBody = pureAlc / (0.65*70);

        //1 promil to ok 7 h, pozostaly czas = 7h/promil * ilosc_promili;
        timeToRest = (long) (1200 * alcInBody);
    }

    public String getDrinkName() {
        return mDrinkName;
    }

    public String getTimeLeft() {
        long hours = timeToRest / 3600;
        long minutes = (timeToRest % 3600) / 60;
        long seconds = timeToRest % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public void setTimeToRest(long newTime){
        timeToRest = newTime;
    }

    public long getTimeToRest() {
        return timeToRest;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public int getVolume() {
        return (int) mVolume;
    }

    public double getActAlcInBody() {
        return actAlcInBody;
    }
}
