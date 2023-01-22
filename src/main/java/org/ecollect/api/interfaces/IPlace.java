package org.ecollect.api.interfaces;

import com.neovisionaries.i18n.CountryCode;

public interface IPlace {
    public String getCity();
    public CountryCode getCountry();
    public IPlace setCity(String city);
    public IPlace setCountry(CountryCode country);
}
