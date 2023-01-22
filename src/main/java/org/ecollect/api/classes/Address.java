package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.neovisionaries.i18n.CountryCode;

import java.util.ArrayList;

public class Address {

    @JsonProperty(required = true)
    private ArrayList<String> address_lines;
    private String district;
    private String zip;

    @JsonProperty(required = true)
    private String city;
    private String state;

    @JsonProperty(required = true)
    private CountryCode country;

    @JsonProperty(defaultValue = "not_specified")
    private TypeEnum type;

    private String value;


    public enum TypeEnum {
        NOT_SPECIFIED("not_specified"),

        BILLING("billing"),

        MAILING("mailing"),

        DELIVERY("delivery"),

        RESIDENCE("residence"),

        EMAIL("email");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }


    public ArrayList<String> getAddress_lines() {
        return address_lines;
    }

    public void setAddress_lines(ArrayList<String> address_lines) {
        this.address_lines = address_lines;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }


    @JsonSetter("country")
    public CountryCode setCountryJson (String country) {
        if (country == null) return null;
        CountryCode ctr = CountryCode.getByCode(country.toUpperCase());
        this.country = ctr;
        return ctr;
    }

    public TypeEnum getType() {
        return type;
    }

    @JsonGetter("type")
    public String getTypeJson() {
        return (type != null) ? type.toString().toLowerCase() : null;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    @JsonSetter("type")
    public TypeEnum setTypeEnum(String value) {
        this.type = TypeEnum.fromValue(value);
        return this.type;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
