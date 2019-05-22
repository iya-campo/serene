package com.example.grey.serene;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {

    final static String FileName = "Serene";

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(FileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static void SharedPrefSave(Context ctx, String userID) {
        SharedPreferences sharedPref = ctx.getSharedPreferences("userID", 0);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("userID", userID);
        editor.commit();
    }
}
