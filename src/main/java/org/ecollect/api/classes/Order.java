package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class Order {

    private String id;
    private StageEnum type;

    private Currency currency;
    private QualityEnum quality;

    private String customer;

    private ArrayList<OrderEvent> events;
    private ArrayList<OrderMetadata> metadata;

    private ArrayList<Claim> principal_claims;
    private ArrayList<Credit> credits;
    private ArrayList<AdditionalCharge> additional_charges;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public ArrayList<OrderEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<OrderEvent> events) {
        this.events = events;
    }

    public ArrayList<OrderMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<OrderMetadata> metadata) {
        this.metadata = metadata;
    }

    public ArrayList<Claim> getPrincipal_claims() {
        return principal_claims;
    }

    public void setPrincipal_claims(ArrayList<Claim> principal_claims) {
        this.principal_claims = principal_claims;
    }

    public ArrayList<Credit> getCredits() {
        return credits;
    }

    public void setCredits(ArrayList<Credit> credits) {
        this.credits = credits;
    }

    public ArrayList<AdditionalCharge> getAdditional_charges() {
        return additional_charges;
    }

    public void setAdditional_charges(ArrayList<AdditionalCharge> charges) {
        this.additional_charges = charges;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }




    @JsonGetter("quality")
    public String getQualityJson() {
        return (quality != null) ? quality.toString().toLowerCase() : null;
    }

    public QualityEnum getQuality() {
        return quality;
    }

    @JsonSetter("quality")
    public QualityEnum setQualityJson(String value) {
        this.quality = QualityEnum.fromValue(value);
        return this.quality;
    }

    public void setQuality(QualityEnum quality) {
        this.quality = quality;
    }




    @JsonGetter("type")
    public String getTypeJson() {
        return (type != null) ? type.toString().toLowerCase() : null;
    }

    public StageEnum getType() {
        return type;
    }

    @JsonSetter("type")
    public QualityEnum setTypeJson(String value) {
        this.type = StageEnum.fromValue(value);
        return this.quality;
    }

    public void setQuality(StageEnum type) {
        this.type = type;
    }







}
