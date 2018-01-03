package com.sumalogos.webster.sumalogos.apiservice

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.sumalogos.webster.sumalogos.util.APIClient
import com.sumalogos.webster.sumalogos.util.APIInterface
import com.sumalogos.webster.sumalogos.util.LiveDataResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by webster on 02/01/18.
 */

open class UserAPIServiceImpl : BaseAPIService(), IUserAPIService {

    private val apiInterface: APIInterface = APIClient.client.create(APIInterface::class.java)

    override fun createUser(fullName: String): LiveData<LiveDataResult<Any>> {
        val liveData = MutableLiveData<LiveDataResult<Any>>()

        try {
            val objectObservable = apiInterface.createUser(fullName)

            objectObservable
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        } catch (e: Exception) {
            Log.d("DUMA", e.message)
        }

        return liveData
    }
}