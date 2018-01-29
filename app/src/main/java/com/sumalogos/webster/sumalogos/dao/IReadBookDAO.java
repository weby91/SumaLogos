package com.sumalogos.webster.sumalogos.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sumalogos.webster.sumalogos.model.ReadBook;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by webster on 24/01/18.
 */

@Dao
public interface IReadBookDAO {
    @Query("select * from ReadBook")
    List<ReadBook> getReadBooks();

    @Query("select * from ReadBook where bookId = :bookId")
    ReadBook getReadBook(int bookId);

    @Insert(onConflict = REPLACE)
    void saveReadBook(ReadBook readBook);
}
