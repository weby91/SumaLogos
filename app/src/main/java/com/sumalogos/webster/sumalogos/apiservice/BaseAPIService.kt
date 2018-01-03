package com.sumalogos.webster.sumalogos.apiservice

import com.sumalogos.webster.sumalogos.util.LiveDataResult
import com.sumalogos.webster.sumalogos.util.Status


/**
 * Created by webster on 02/01/18.
 */

open class BaseAPIService : IBaseAPIService {
    override fun <T> storeResult(`object`: T): LiveDataResult<T> {
        return LiveDataResult(Status.SUCCESS, `object`, "")
    }

    override fun <T> storeError(error: Throwable): LiveDataResult<T> {
        return LiveDataResult(Status.ERROR, null, error.message)
    }
}