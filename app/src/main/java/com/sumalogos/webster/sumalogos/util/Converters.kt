package com.sumalogos.webster.sumalogos.util

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Created by webster on 01/01/18.
 */

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return (date?.time)
    }
}