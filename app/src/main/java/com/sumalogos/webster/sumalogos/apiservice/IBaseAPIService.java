package com.sumalogos.webster.sumalogos.apiservice;

import com.sumalogos.webster.sumalogos.util.LiveDataResult;

/**
 * Created by webster on 18/01/18.
 */

public interface IBaseAPIService {
    <T> LiveDataResult<?> storeResult(T object, String methodName, String tag);

    <T> LiveDataResult<T> storeError(Throwable error, String methodName, String tag);
}
