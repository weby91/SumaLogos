package com.sumalogos.webster.sumalogos.util;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sumalogos.webster.sumalogos.model.Devotion;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by webster on 22/01/18.
 */

public class DataTypeConverters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Devotion> devotionToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Devotion>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String devotionListToString(List<Devotion> objects) {
        return gson.toJson(objects);
    }
}
