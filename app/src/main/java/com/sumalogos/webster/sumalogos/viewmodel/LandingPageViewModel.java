package com.sumalogos.webster.sumalogos.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import com.sumalogos.webster.sumalogos.apiservice.DevotionAPIServiceImpl;
import com.sumalogos.webster.sumalogos.apiservice.IDevotionAPIService;
import com.sumalogos.webster.sumalogos.apiservice.IUserAPIService;
import com.sumalogos.webster.sumalogos.apiservice.UserAPIServiceImpl;
import com.sumalogos.webster.sumalogos.model.User;
import com.sumalogos.webster.sumalogos.util.LiveDataResult;

import org.json.JSONObject;

/**
 * Created by webster on 18/01/18.
 */

public class LandingPageViewModel extends BaseViewModel {

    public LiveData<LiveDataResult<?>> createUser(User user) {
        MediatorLiveData<LiveDataResult<?>> mediatorLiveData = new MediatorLiveData<>();
        IUserAPIService userAPIService = new UserAPIServiceImpl();

        mediatorLiveData.addSource(
                userAPIService.createUser(user),
                mediatorLiveData::setValue
        );

        return mediatorLiveData;
    }

    public User initFacebookUser(User user, JSONObject o) {
        try {
            user.setAndroidId(BaseViewModel.getAndroidId());
            user.setAppVersion(BaseViewModel.getAppVersion());
            user.setDeviceModel(BaseViewModel.getDeviceModel());
            user.setEmail(o.getString("email"));
            user.setFullName(o.getString("name"));
            user.setFullName(o.getString("name"));
            user.setGender(o.getString("gender"));
            user.setRegisteredVia("Facebook");

            // Social Media
            user.setSocialMediaId(o.getString("id"));

            JSONObject pictureObject = o.getJSONObject("picture").getJSONObject("data");

            user.setSocialMediaPicture(pictureObject.getString("url"));
            user.setSocialMediaLink(o.getString("link"));

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public User initGoogleUser(User user) {
        try {
            user.setAndroidId(BaseViewModel.getAndroidId());
            user.setAppVersion(BaseViewModel.getAppVersion());
            user.setDeviceModel(BaseViewModel.getDeviceModel());
            user.setGender("");
            user.setSocialMediaLink("");
            user.setRegisteredVia("Google");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    public LiveData<LiveDataResult<?>> retrieveDevotions() {
        MediatorLiveData<LiveDataResult<?>> mediatorLiveData = new MediatorLiveData<>();
        IDevotionAPIService apiService = new DevotionAPIServiceImpl();

        mediatorLiveData.addSource(
                apiService.retrieveDevotions(),
                mediatorLiveData::setValue
        );

        return mediatorLiveData;
    }
}
