package com.tr.countries.data;

import java.util.List;
/**
 * Countries Data Model
 */

public class Countries {
    public List<Date> data;
    public List<LinkOfCountry> links;
    public MetadataOfCountry metadata;


}

class LinkOfCountry {
    String rel;
    String href;
}

class MetadataOfCountry{
    Long currentOffset;
    Long totalCount;
}

