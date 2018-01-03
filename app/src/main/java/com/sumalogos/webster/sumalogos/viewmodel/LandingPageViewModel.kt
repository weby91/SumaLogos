package com.sumalogos.webster.sumalogos.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.sumalogos.webster.sumalogos.apiservice.IUserAPIService
import com.sumalogos.webster.sumalogos.apiservice.UserAPIServiceImpl
import com.sumalogos.webster.sumalogos.util.LiveDataResult

/**
 * Created by webster on 02/01/18.
 */


class LandingPageViewModel {

    fun createUser(fullName: String): LiveData<LiveDataResult<*>> {
        val mediatorLiveData: MediatorLiveData<LiveDataResult<*>> = MediatorLiveData()
        val userAPIService: IUserAPIService = UserAPIServiceImpl()

        mediatorLiveData.addSource(
                userAPIService.createUser(fullName), { mediatorLiveData.value })

        return mediatorLiveData
    }
}
