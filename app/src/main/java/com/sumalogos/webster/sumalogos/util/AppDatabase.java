package com.sumalogos.webster.sumalogos.util;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.sumalogos.webster.sumalogos.MyApplication;
import com.sumalogos.webster.sumalogos.dao.IDevotionDAO;
import com.sumalogos.webster.sumalogos.dao.IReadBookDAO;
import com.sumalogos.webster.sumalogos.dao.IUserDAO;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.model.ReadBook;
import com.sumalogos.webster.sumalogos.model.User;

/**
 * Created by webster on 20/01/18.
 */

@Database(entities = {User.class, Devotion.class, ReadBook.class}, exportSchema = false, version = 1)
@TypeConverters({DataTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(MyApplication.getContext(),
                    AppDatabase.class, "julo_db")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract IUserDAO userDAO();
    public abstract IDevotionDAO devotionDAO();
    public abstract IReadBookDAO readBookDAO();
}
