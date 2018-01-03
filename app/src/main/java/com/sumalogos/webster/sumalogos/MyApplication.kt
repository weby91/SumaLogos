package com.sumalogos.webster.sumalogos

/**
 * Created by webster on 01/01/18.
 */
import android.app.Application
import com.sumalogos.webster.sumalogos.util.AppDatabase

/**
 * Created by webster on 01/01/18.
 */
class MyApplication : Application() {
    private var appDatabase: AppDatabase? = null

    fun getAppDatabase(): AppDatabase {
        if (appDatabase == null) {
            appDatabase = AppDatabase.getAppDatabase(this)
        }

        return appDatabase as AppDatabase
    }
}