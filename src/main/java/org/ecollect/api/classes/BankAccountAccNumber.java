package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.IBankAccountType;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class BankAccountAccNumber implements IBankAccountType {

    @JsonIgnore
    private final BankAccount.bankAccountTypeEnum bankAccountType = BankAccount.bankAccountTypeEnum.AccNumber;

    @JsonProperty(required = true)
    private String account_holder;

    @JsonProperty(required = true)
    private String account_number;

    @JsonProperty(required = true)
    private String clearing_number;

    @JsonProperty(required = true)
    private CountryCode bank_country;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;


    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getClearing_number() {
        return clearing_number;
    }

    public void setClearing_number(String clearing_number) {
        this.clearing_number = clearing_number;
    }

    public CountryCode getBank_country() {
        return bank_country;
    }

    public void setBank_country(CountryCode bank_country) {
        this.bank_country = bank_country;
    }

    @JsonSetter("bank_country")
    public CountryCode setBank_country(String country) {
        if (country == null) return null;
        CountryCode ctr = CountryCode.getByCode(country.toUpperCase());
        this.bank_country = ctr;
        return ctr;
    }

    public String getAccount_holder() {
        return account_holder;
    }


    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }

    public BankAccountAccNumber BankAccountAccNumber() {return this;}



    public LocalDateTime getCreated_at() {
        return created_at;
    }


    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public BankAccount.bankAccountTypeEnum getBankAccountType() {
        return bankAccountType;
    }

}
