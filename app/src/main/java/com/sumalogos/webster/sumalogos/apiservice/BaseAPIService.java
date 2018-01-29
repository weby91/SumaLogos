package com.sumalogos.webster.sumalogos.apiservice;

import com.sumalogos.webster.sumalogos.util.LiveDataResult;
import com.sumalogos.webster.sumalogos.util.Status;

/**
 * Created by webster on 18/01/18.
 */

public class BaseAPIService implements IBaseAPIService {
    String methodName = "";

    @Override
    public <T> LiveDataResult<T> storeResult(T object, String methodName, String tag) {
        return new LiveDataResult<>(Status.SUCCESS, object, "");
    }

    @Override
    public <T> LiveDataResult<T> storeError(Throwable error, String methodName, String tag) {
        return new LiveDataResult<>(Status.ERROR, null, error.getMessage());
    }
}
