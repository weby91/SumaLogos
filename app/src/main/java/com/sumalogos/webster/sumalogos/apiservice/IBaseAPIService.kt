package com.sumalogos.webster.sumalogos.apiservice

import com.sumalogos.webster.sumalogos.util.LiveDataResult



/**
 * Created by webster on 02/01/18.
 */

interface IBaseAPIService {
    fun <T> storeResult(`object`: T): LiveDataResult<*>

    fun <T> storeError(error: Throwable): LiveDataResult<T>
}