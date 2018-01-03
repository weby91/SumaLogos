package com.sumalogos.webster.sumalogos.apiservice

import android.arch.lifecycle.LiveData
import com.sumalogos.webster.sumalogos.util.LiveDataResult


/**
 * Created by webster on 02/01/18.
 */

interface IUserAPIService {
    fun createUser(fullName: String): LiveData<LiveDataResult<Any>>
}