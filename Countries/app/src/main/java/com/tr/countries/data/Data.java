package com.tr.countries.data;

public class Data {
    public String capital;
    public String code;
    public String callingCode;
    public String[] currencyCodes;
    public String flagImageURI;
    public String name;
    public long numRegions;
    public String wikiDataID;

    public Data(String capital, String code, String callingCode, String[] currencyCodes, String flagImageURI, String name, long numRegions, String wikiDataID) {
        this.capital = capital;
        this.code = code;
        this.callingCode = callingCode;
        this.currencyCodes = currencyCodes;
        this.flagImageURI = flagImageURI;
        this.name = name;
        this.numRegions = numRegions;
        this.wikiDataID = wikiDataID;
    }
}
