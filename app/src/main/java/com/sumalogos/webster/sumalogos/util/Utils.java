package com.sumalogos.webster.sumalogos.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by webster on 23/01/18.
 */

public class Utils {
    private static SharedPreferences pref;

    private static Utils instance;

    public static Utils getInstance(Context context) {
        if (instance == null) {
            instance = new Utils();

            pref = context.getSharedPreferences("MyPref", 0);

        }

        return instance;
    }
    public void setIsLogin(boolean isLogin) {
        pref.edit().putBoolean("isLogin", isLogin).apply();
    }

    public boolean isLogin() {
        return pref.getBoolean("isLogin", false);
    }
}
