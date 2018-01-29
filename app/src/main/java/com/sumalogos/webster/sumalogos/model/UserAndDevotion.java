package com.sumalogos.webster.sumalogos.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by webster on 24/01/18.
 */

public class UserAndDevotion {
    @SerializedName("user")
    private User user;

    @SerializedName("devotional")
    private List<Devotion> devotions;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Devotion> getDevotions() {
        return devotions;
    }

    public void setDevotion(List<Devotion> devotions) {
        this.devotions = devotions;
    }
}
