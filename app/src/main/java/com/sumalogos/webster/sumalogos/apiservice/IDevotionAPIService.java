package com.sumalogos.webster.sumalogos.apiservice;

import android.arch.lifecycle.LiveData;

import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.model.Result;
import com.sumalogos.webster.sumalogos.model.UserAndDevotion;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

import java.util.List;

/**
 * Created by webster on 22/01/18.
 */

public interface IDevotionAPIService {
    LiveData<LiveDataResult<Result<List<Devotion>>>> retrieveDevotions();

    LiveData<LiveDataResult<Result<List<Devotion>>>> retrieveDevotionsV2(long userId);

    LiveData<LiveDataResult<UserAndDevotion>> finishRead(long userId, String action, int devotionalId);
}
