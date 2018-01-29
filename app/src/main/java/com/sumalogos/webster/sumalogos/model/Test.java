package com.sumalogos.webster.sumalogos.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webster on 22/01/18.
 */

@Entity
public class Test {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @PrimaryKey
    @SerializedName("id")
    private long id;
}
