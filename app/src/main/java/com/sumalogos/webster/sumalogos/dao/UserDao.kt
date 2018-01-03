package com.sumalogos.webster.sumalogos.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.sumalogos.webster.sumalogos.model.User


/**
 * Created by webster on 01/01/18.
 */

@Dao
interface UserDao {

    @get:Query("SELECT * FROM user")
    val user: User

    @Insert
    fun insert(user: User)

    @Delete
    fun delete(user: User)
}