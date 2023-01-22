package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.IEntityType;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class Person implements IEntityType {

    @JsonIgnore
    private final String entityType = "Person";

    @JsonProperty(required = true)
    private String given_names;
    @JsonProperty(required = true)
    private String surname;
    private SexEnum sex = SexEnum.U;
    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date_of_birth;
    private Place place_of_birth;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date_of_death;
    private Place place_of_death;
    private CountryCode nationality;
    private CountryCode country_of_residence;


    public String getGiven_names() {
        return given_names;
    }

    public void setGiven_names(String given_names) {
        this.given_names = given_names;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public SexEnum getSex() {
        return sex;
    }

    @JsonGetter("sex")
    public String getSexJson() {
        return (sex != null) ? sex.toString().toLowerCase() : null;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @JsonSetter("sex")
    public SexEnum setSexEnum(String value) {
        this.sex = Person.SexEnum.fromValue(value);
        return this.sex;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public LocalDateTime getDate_of_birth() {
        return date_of_birth;
    }



    public void setDate_of_birth(LocalDateTime date_of_birth) {
        this.date_of_birth = date_of_birth;
    }



    public Place getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(Place place_of_birth) {
        this.place_of_birth = place_of_birth;
    }



    public LocalDateTime getDate_of_death() {
        return date_of_death;
    }


    public void setDate_of_death(LocalDateTime date_of_death) {
        this.date_of_death = date_of_death;
    }




    public Place getPlace_of_death() {
        return place_of_death;
    }

    public void setPlace_of_death(Place place_of_death) {
        this.place_of_death = place_of_death;
    }

    public CountryCode getNationality() {
        return nationality;
    }

    public void setNationality(CountryCode nationality) {
        this.nationality = nationality;
    }


    @JsonSetter("nationality")
    public CountryCode setNationalityJson (String country) {
        if (country == null) return null;
        CountryCode ctr = CountryCode.getByCode(country.toUpperCase());
        this.nationality = ctr;
        return ctr;
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

    public enum SexEnum {
        M("m"),

        F("f"),

        X("x"),

        U("u");

        private String value;

        SexEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value).toLowerCase();
        }

        public static SexEnum fromValue(String value) {
            for (SexEnum b : SexEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }


}
