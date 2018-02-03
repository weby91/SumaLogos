package com.sumalogos.webster.sumalogos.apiservice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.sumalogos.webster.sumalogos.BuildConfig;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.util.APIClient;
import com.sumalogos.webster.sumalogos.util.APIInterface;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by webster on 18/01/18.
 */

public class UserAPIServiceImpl extends BaseAPIService implements IUserAPIService {
    private APIInterface apiInterface;

    public UserAPIServiceImpl() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public LiveData<LiveDataResult<JsonObject>> createUser(User user) {
        final MutableLiveData<LiveDataResult<JsonObject>> liveData = new MutableLiveData<>();

        try {

            apiInterface.createUser(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            result -> liveData.setValue(storeResult(result, "", "")),
                            error -> liveData.setValue(storeError(error, "", ""))
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liveData;
    }

    @Override
    public LiveData<LiveDataResult> updateLoginDt(long id) {
        final MutableLiveData<LiveDataResult> liveData = new MutableLiveData<>();

        try {

            apiInterface.updateLoginDt(id, BuildConfig.VERSION_NAME)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            () -> liveData.setValue(storeResult(null, "", "")),
                            error -> liveData.setValue(storeError(error, "", ""))
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liveData;
    }

    @Override
    public LiveData<LiveDataResult<User>> retrieveReadingProgress(long userId) {
        final MutableLiveData<LiveDataResult<User>> liveData = new MutableLiveData<>();

        try {

            apiInterface.retrieveReadingProgress(userId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            result -> liveData.setValue(storeResult(result, "", "")),
                            error -> liveData.setValue(storeError(error, "", ""))
                    );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return liveData;
    }
}
