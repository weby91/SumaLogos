package com.sumalogos.webster.sumalogos.apiservice;

import android.arch.lifecycle.LiveData;

import com.google.gson.JsonObject;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

/**
 * Created by webster on 18/01/18.
 */

public interface IUserAPIService {
    LiveData<LiveDataResult<JsonObject>> createUser(User user);

    LiveData<LiveDataResult> updateLoginDt(long id);

    LiveData<LiveDataResult<User>> retrieveReadingProgress(long userId);
}
