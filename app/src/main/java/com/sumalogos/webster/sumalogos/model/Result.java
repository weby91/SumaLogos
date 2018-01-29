package com.sumalogos.webster.sumalogos.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webster on 22/01/18.
 */

public class Result<T> {
    @SerializedName("status")
    private String status;

    @SerializedName("data")
    @Nullable
    private T data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public void setData(@Nullable T data) {
        this.data = data;
    }
}
