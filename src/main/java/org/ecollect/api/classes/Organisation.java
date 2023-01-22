package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.interfaces.IEntityType;

public class Organisation implements IEntityType {

    @JsonIgnore
    private final String entityType = "Organisation";

    @JsonProperty(required = true)
    private String name;
    private String type;
    private CountryCode country_of_residence;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public CountryCode getCountry_of_residence() {
        return country_of_residence;
    }

    public void setCountry_of_residence(CountryCode country_of_residence) {
        this.country_of_residence = country_of_residence;
    }

    @JsonSetter("country_of_residence")
    public CountryCode setCountry_of_residenceJson (String country) {
        if (country == null) return null;
        CountryCode ctr = CountryCode.getByCode(country.toUpperCase());
        this.country_of_residence = ctr;
        return ctr;
    }



}
