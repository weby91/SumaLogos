package com.sumalogos.webster.sumalogos.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.sumalogos.webster.sumalogos.model.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by webster on 20/01/18.
 */

@Dao
public interface IUserDAO {
    @Query("select * from User")
    User getUser();

    @Query("select id from User")
    long getUserId();

    @Query("select readingProgress from User")
    int getReadingProgress();

    @Insert(onConflict = REPLACE)
    void addUser(User user);

    @Update
    void updateUser(User user);
}
