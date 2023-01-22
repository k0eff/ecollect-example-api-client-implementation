package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.interfaces.IPlace;

public class Place implements IPlace {
    private String city;
    private CountryCode country;


    public String getCity() {
        return city;
    }

    public Place setCity(String city) {
        this.city = city;
        return this;
    }

    public CountryCode getCountry() {
        return country;
    }

    public Place setCountry(CountryCode country) {
        this.country = country;
        return this;
    }

    @JsonSetter("country")
    public CountryCode setCountry_of_residenceJson (String country) {
        if (country == null) return null;
        CountryCode ctr = CountryCode.getByCode(country.toUpperCase());
        this.country = ctr;
        return ctr;
    }


}
