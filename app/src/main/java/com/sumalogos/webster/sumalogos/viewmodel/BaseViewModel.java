package com.sumalogos.webster.sumalogos.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.os.Build;
import android.provider.Settings;

import com.sumalogos.webster.sumalogos.BuildConfig;
import com.sumalogos.webster.sumalogos.MyApplication;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.util.AppDatabase;

import java.util.List;

/**
 * Created by webster on 20/01/18.
 */

public class BaseViewModel extends ViewModel {
    protected AppDatabase db = AppDatabase.getInstance();

    @SuppressLint("HardwareIds")
    public static String getAndroidId() {
        try {
            return Settings.Secure.getString(MyApplication.getContext()
                        .getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public static String getDeviceModel() {
        return Build.BRAND + " | " + Build.MODEL + " " + Build.VERSION.RELEASE
                + " " + Build.VERSION_CODES.class
                .getFields()[android.os.Build.VERSION.SDK_INT].getName();
    }

    public static void setUser(User user) {
        AppDatabase db = AppDatabase.getInstance();
        db.userDAO().addUser(user);
    }

    public static void addDevotions(List<Devotion> devotions) {
        AppDatabase db = AppDatabase.getInstance();
        db.devotionDAO().addDevotions(devotions);
    }
}
