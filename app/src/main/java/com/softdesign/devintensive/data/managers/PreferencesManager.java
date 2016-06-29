package com.softdesign.devintensive.data.managers;


import android.content.SharedPreferences;


import java.util.ArrayList;
import java.util.List;


import com.softdesign.devintensive.utils.ConstantManager;
import com.softdesign.devintensive.utils.DevintensiveApplication;

public class PreferencesManager {


    private SharedPreferences mSharedPreferences;
    private static final String[] KEYS_FIELD = {ConstantManager.USER_PHONE_TAG, ConstantManager.USER_EMAIL_TAG,
            ConstantManager.USER_VK_TAG, ConstantManager.USER_GIT_TAG, ConstantManager.USER_ABOUT_TAG};


    public PreferencesManager() {
        mSharedPreferences = DevintensiveApplication.getSharedPreferences();
    }


    public void saveUserData(List<String> dataByKey){
        SharedPreferences.Editor editor = mSharedPreferences.edit();


        for (int i = 0; i < KEYS_FIELD.length; i++) {
            editor.putString(KEYS_FIELD[i], dataByKey.get(i));
        }
        editor.apply();
    }


    public List<String> loadUserData(){
        List<String> dataByKey = new ArrayList<>();
        for (int i = 0; i < KEYS_FIELD.length; i++){
            dataByKey.add(mSharedPreferences.getString(KEYS_FIELD[i], "null"));
        }
        return dataByKey;
    }
}
