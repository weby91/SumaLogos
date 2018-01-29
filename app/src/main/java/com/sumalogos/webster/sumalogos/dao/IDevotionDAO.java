package com.sumalogos.webster.sumalogos.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sumalogos.webster.sumalogos.model.Devotion;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by webster on 22/01/18.
 */

@Dao
public interface IDevotionDAO {
    @Query("select * from Devotion")
    List<Devotion> getDevotions();

    @Query("select * from Devotion where id = :id")
    Devotion getDevotion(int id);

    @Insert(onConflict = REPLACE)
    void addDevotions(List<Devotion> devotions);

    @Update
    void updateDevotion(Devotion devotion);

    @Update
    void updateDevotions(List<Devotion> devotions);

    @Query("select * from Devotion")
    Devotion getDevotion();
}
