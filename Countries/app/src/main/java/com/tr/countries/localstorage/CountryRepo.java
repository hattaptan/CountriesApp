package com.tr.countries.localstorage;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.tr.countries.data.FavoriteCounty;

import java.util.List;

public class CountryRepo {

    private CountryDao countryDao;
    private LiveData<List<FavoriteCounty>> mAllWords;

    public CountryRepo(Application application) {
        CountryDatabase db = CountryDatabase.getDatabase(application);
        countryDao = db.countryDao();
    }

    public List<FavoriteCounty> getAll() {
        return countryDao.getAll();
    }

    public void insert(FavoriteCounty favoriteCounty) {
        CountryDatabase.databaseWriteExecutor.execute(() -> {
            countryDao.insertAll(favoriteCounty);
        });
    }
}
