package com.tr.countries.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
/*
Local Db to keep favorite country
but didnt complete
 */
@Entity(tableName = "country")
public class FavoriteCounty {
    @PrimaryKey
    @NonNull
    public UUID uuid = UUID.randomUUID();
    public String code;
    public String name;
    public String wikiDataID;

    public FavoriteCounty(String code, String name, String wikiDataID) {
        this.code = code;
        this.name = name;
        this.wikiDataID = wikiDataID;
    }
}
