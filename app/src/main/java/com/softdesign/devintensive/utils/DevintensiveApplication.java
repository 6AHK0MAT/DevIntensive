package com.softdesign.devintensive.utils;


import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import com.softdesign.devintensive.ui.activities.AuthActivity;


public class DevintensiveApplication extends Application {


    private static SharedPreferences sSharedPreferences;


    public static SharedPreferences getSharedPreferences() {
        return sSharedPreferences;
    }


    @Override
    public void onCreate() {
        super.onCreate();


        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }
}