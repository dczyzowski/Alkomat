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
    long startTime;
    double mVolume;
    double mQuantity;
    double mWeight;
    double actAlcInBody;
    Calendar calendar;

    public DrinkModel(String drinkName, double volume, double quantity, double weight){
        mDrinkName = drinkName;
        mVolume = volume;
        mQuantity = quantity;
        mWeight = weight;

        // masa alkoholu = ilosc wypitego napoju w ml * zawartosc procentowa * 0.08 waga
        double pureAlc = mQuantity*mVolume*0.01*0.8;

        // zawartosc alkoholu we krwi = ilosc spozytego alkoholu/ (wspolczynnik * waga osoby)
        double alcInBody = pureAlc / (0.65*mWeight);
        //1 promil to ok 7 h, pozostaly czas = 7h/promil * ilosc_promili;
        double timeLeft =  25200 * alcInBody;
        final long time = (long) timeLeft;
        actAlcInBody = alcInBody;

        new CountDownTimer(time * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeToRest = millisUntilFinished/1000 - 2;
                actAlcInBody = (time-timeToRest)/(7*360);
            }

            public void onFinish() {
            }
        }.start();


    }

    public DrinkModel(String drinkName, double volume, double quantity, double weight, long addTime){
        mDrinkName = drinkName;
        mVolume = volume;
        mQuantity = quantity;
        mWeight = weight;

        // masa alkoholu = ilosc wypitego napoju w ml * zawartosc procentowa * 0.08 waga
        double pureAlc = mQuantity*mVolume*0.01*0.8;

        // zawartosc alkoholu we krwi = ilosc spozytego alkoholu/ (wspolczynnik * waga osoby)
        double alcInBody = pureAlc / (0.65*mWeight);
        //1 promil to ok 7 h, pozostaly czas = 7h/promil * ilosc_promili;
        double timeLeft =  25200 * alcInBody;
        final long time = addTime + (long) timeLeft;

        new CountDownTimer(time*1000, 1000) {

            public void onTick(long millisUntilFinished) {
                timeToRest = millisUntilFinished/1000;
                actAlcInBody = (time-timeToRest)/(7*360);
            }

            public void onFinish() {
            }
        }.start();
    }

    public String getDrinkName() {
        return mDrinkName;
    }

    public String getTimeLeft() {

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return formatter.format(new Date(timeToRest*1000));
    }

    public long getTimeToRest() {
        return timeToRest;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public int getVolume() {
        return (int) mVolume;
    }

    public double getActAlcInBody() {
        return actAlcInBody;
    }
}
