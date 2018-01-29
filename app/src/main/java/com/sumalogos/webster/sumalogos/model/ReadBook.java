package com.sumalogos.webster.sumalogos.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by webster on 24/01/18.
 */

@Entity
public class ReadBook {
    @PrimaryKey
    private int bookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
