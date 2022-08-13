package com.tr.countries.localstorage;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tr.countries.data.CountryDom;
import com.tr.countries.data.Date;
import com.tr.countries.data.FavoriteCounty;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = FavoriteCounty.class,version = 1)
public abstract class CountryDatabase extends RoomDatabase {
    public abstract CountryDao countryDao();

    private static volatile CountryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CountryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CountryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CountryDatabase.class, "country")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
