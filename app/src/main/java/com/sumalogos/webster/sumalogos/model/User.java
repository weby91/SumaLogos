package com.sumalogos.webster.sumalogos.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webster on 20/01/18.
 */

@Entity
public class User {
    @PrimaryKey
    @SerializedName("id")
    private long id;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("email")
    private String email;

    @SerializedName("gender")
    private String gender;

    @SerializedName("social_media_id")
    private String socialMediaId;

    @SerializedName("social_media_picture")
    private String socialMediaPicture;

    @SerializedName("social_media_link")
    private String socialMediaLink;

    @SerializedName("registered_via")
    private String registeredVia;

    @SerializedName("android_id")
    private String androidId;

    @SerializedName("device_model")
    private String deviceModel;

    @SerializedName("app_version")
    private String appVersion;

    @SerializedName("reading_progress")
    private int readingProgress;

    @SerializedName("fcm_token")
    private String fcmToken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(String socialMediaId) {
        this.socialMediaId = socialMediaId;
    }

    public String getSocialMediaPicture() {
        return socialMediaPicture;
    }

    public void setSocialMediaPicture(String socialMediaPicture) {
        this.socialMediaPicture = socialMediaPicture;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    public String getRegisteredVia() {
        return registeredVia;
    }

    public void setRegisteredVia(String registeredVia) {
        this.registeredVia = registeredVia;
    }

    public String getAndroidId() {
        return androidId;
    }

    public void setAndroidId(String androidId) {
        this.androidId = androidId;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public int getReadingProgress() {
        return readingProgress;
    }

    public void setReadingProgress(int readingProgress) {
        this.readingProgress = readingProgress;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }
}
