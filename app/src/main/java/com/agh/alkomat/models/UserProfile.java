package com.agh.alkomat.models;

/**
 * Created by Damian on 2017-06-05.
 */

public class UserProfile {

    double mWeight;
    String mName;

    UserProfile(){

    }

    public UserProfile(String name, double weight){
        mName = name;
        mWeight = weight;
    }

    public double getWeight() {
        return mWeight;
    }

    public String getName() {
        return mName;
    }
}
