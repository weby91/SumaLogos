package com.sumalogos.webster.sumalogos.apiservice;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.model.Result;
import com.sumalogos.webster.sumalogos.model.UserAndDevotion;
import com.sumalogos.webster.sumalogos.util.APIClient;
import com.sumalogos.webster.sumalogos.util.APIInterface;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by webster on 22/01/18.
 */

public class DevotionAPIServiceImpl extends BaseAPIService implements IDevotionAPIService {
    private APIInterface apiInterface;

    public DevotionAPIServiceImpl() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    @Override
    public LiveData<LiveDataResult<Result<List<Devotion>>>> retrieveDevotions() {
        final MutableLiveData<LiveDataResult<Result<List<Devotion>>>> liveData = new MutableLiveData<>();

        try {

            apiInterface.retrieveDevotions()
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
    public LiveData<LiveDataResult<Result<List<Devotion>>>> retrieveDevotionsV2(long userId) {
        final MutableLiveData<LiveDataResult<Result<List<Devotion>>>> liveData = new MutableLiveData<>();

        try {

            apiInterface.retrieveDevotionsV2(userId)
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
    public LiveData<LiveDataResult<UserAndDevotion>> finishRead(long userId, String action, int devotionalId) {
        final MutableLiveData<LiveDataResult<UserAndDevotion>> liveData = new MutableLiveData<>();

        try {

            apiInterface.finishRead(userId, action, devotionalId)
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
