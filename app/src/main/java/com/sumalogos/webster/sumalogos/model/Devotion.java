package com.sumalogos.webster.sumalogos.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by webster on 04/01/18.
 */

@Entity
public class Devotion {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("day")
    private int day;

    @SerializedName("week_day")
    private String weekDay;

    @SerializedName("date")
    private String date;

    @SerializedName("book")
    private String book;

    @SerializedName("total_read")
    private int totalRead;

    @SerializedName("total_share")
    private int totalShare;

    @SerializedName("total_discussion")
    private int totalDiscussion;

    @SerializedName("book_param")
    private String bookParam;

    private boolean isFinished = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getTotalRead() {
        return totalRead;
    }

    public void setTotalRead(int totalRead) {
        this.totalRead = totalRead;
    }

    public int getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(int totalShare) {
        this.totalShare = totalShare;
    }

    public int getTotalDiscussion() {
        return totalDiscussion;
    }

    public void setTotalDiscussion(int totalDiscussion) {
        this.totalDiscussion = totalDiscussion;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getBookParam() {
        return bookParam;
    }

    public void setBookParam(String bookParam) {
        this.bookParam = bookParam;
    }
}
