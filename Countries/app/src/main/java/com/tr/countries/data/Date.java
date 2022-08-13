package com.tr.countries.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

public class Date {
    public String code;
    public List<String> currencyCodes;
    public String name;
    public String wikiDataID;

    public Date(String code, List<String> currencyCodes, String name, String wikiDataID) {
        this.code = code;
        this.currencyCodes = currencyCodes;
        this.name = name;
        this.wikiDataID = wikiDataID;
    }
}
