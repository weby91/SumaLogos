package com.sumalogos.webster.sumalogos.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.sumalogos.webster.sumalogos.model.Test;

/**
 * Created by webster on 22/01/18.
 */

@Dao
public interface ITestDAO {
    @Query("select * from Test")
    Test getTest();
}
