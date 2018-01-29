package com.sumalogos.webster.sumalogos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.sumalogos.webster.sumalogos.apiservice.DevotionAPIServiceImpl;
import com.sumalogos.webster.sumalogos.apiservice.IDevotionAPIService;
import com.sumalogos.webster.sumalogos.apiservice.IUserAPIService;
import com.sumalogos.webster.sumalogos.apiservice.UserAPIServiceImpl;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

/**
 * Created by webster on 21/01/18.
 */

public class HomeViewModel extends BaseViewModel {
    public LiveData<LiveDataResult<?>> updateLoginDt(long id) {
        MediatorLiveData<LiveDataResult<?>> mediatorLiveData = new MediatorLiveData<>();
        IUserAPIService userAPIService = new UserAPIServiceImpl();

        mediatorLiveData.addSource(
                userAPIService.updateLoginDt(id),
                mediatorLiveData::setValue
        );

        return mediatorLiveData;
    }

    public LiveData<LiveDataResult<?>> finishRead(long userId, String action, int devotionalId) {
        MediatorLiveData<LiveDataResult<?>> mediatorLiveData = new MediatorLiveData<>();
        IDevotionAPIService apiService = new DevotionAPIServiceImpl();

        mediatorLiveData.addSource(
                apiService.finishRead(userId, action, devotionalId),
                mediatorLiveData::setValue
        );

        return mediatorLiveData;
    }

    public LiveData<LiveDataResult<?>> retrieveReadingProgress(long userId) {
        MediatorLiveData<LiveDataResult<?>> mediatorLiveData = new MediatorLiveData<>();
        IUserAPIService userAPIService = new UserAPIServiceImpl();

        mediatorLiveData.addSource(
                userAPIService.retrieveReadingProgress(userId),
                mediatorLiveData::setValue
        );

        return mediatorLiveData;
    }
}
