package com.sumalogos.webster.sumalogos.util

import io.reactivex.Completable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


/**
 * Created by webster on 02/01/18.
 */
interface APIInterface {

@FormUrlEncoded
@POST("http://139.59.241.229:3000/api/v1/user")
fun createUser(
        @Field("full_name") fullName: String): Completable
}