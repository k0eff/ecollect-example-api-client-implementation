package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MatchedConciliation {

    private String id;
    private String file;
    private Amount amount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime value_date;

    private PayeeEnum payee;

    private String account;
    private String customer;
    private ArrayList<String> entries;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<String> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<String> entries) {
        this.entries = entries;
    }




    public PayeeEnum getPayeeEnum() {
        return payee;
    }

    @JsonGetter("payee")
    public String getPayeeEnumJson() {
        return (payee != null) ? payee.toString().toLowerCase() : null;
    }

    public void setPayeeEnum(PayeeEnum payee) {
        this.payee = payee;
    }

    @JsonSetter("payee")
    public PayeeEnum setPayeeEnumJson(String value) {
        this.payee = PayeeEnum.fromValue(value);
        return this.payee;
    }





}
