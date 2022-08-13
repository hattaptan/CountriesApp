package com.tr.countries.ui.home;

import com.tr.countries.data.CountryDom;
import com.tr.countries.data.Date;

public interface AdapterListener {
    void itemClick(String rowName);
    void addToFavorite(Date date);
}
