package com.sumalogos.webster.sumalogos.util;

import com.google.gson.JsonObject;
import com.sumalogos.webster.sumalogos.model.Devotion;
import com.sumalogos.webster.sumalogos.model.Result;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.model.UserAndDevotion;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by webster on 20/01/18.
 */

public interface APIInterface {
    @Headers("Content-Type: application/json")
    @POST(AppConstant.USER)
    Single<JsonObject> createUser(
        @Body User user
    );

    @FormUrlEncoded
    @POST(AppConstant.DEVOTION)
    Single<UserAndDevotion> finishRead(
            @Field("user_id") long userId,
            @Field("action") String action,
            @Field("devotional_id") int devotionalId);

    @FormUrlEncoded
    @PATCH(AppConstant.USER)
    Completable updateLoginDt(
        @Field("id") long id
    );

    @GET(AppConstant.DEVOTION)
    Single<Result<List<Devotion>>> retrieveDevotions();

    @GET(AppConstant.DEVOTION_V2)
    Single<Result<List<Devotion>>> retrieveDevotionsV2(
            @Query("user_id") long userId);

    @GET(AppConstant.USER)
    Single<User> retrieveReadingProgress(
        @Query("user_id") long userId);
}

