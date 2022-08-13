package com.tr.countries.localstorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.tr.countries.data.CountryDom;
import com.tr.countries.data.Date;
import com.tr.countries.data.FavoriteCounty;

import java.util.List;
/*
interface for db methods
 */
@Dao
public interface CountryDao {
    @Query("SELECT * FROM country")
    List<FavoriteCounty> getAll();

    @Insert
    void insertAll(FavoriteCounty favoriteCounty);

    @Delete
    void delete(FavoriteCounty favoriteCounty);
}

