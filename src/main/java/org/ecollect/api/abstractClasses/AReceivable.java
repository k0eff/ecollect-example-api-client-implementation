package org.ecollect.api.abstractClasses;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.classes.*;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;
import org.ecollect.api.utils.IdDenormalizer;

import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class AReceivable {

    @JsonIgnore
    public final String type = null;


    private String your_reference;

    @JsonProperty(required = true)
    private String customer; // property is denormalized
    private String file; // property is denormalized


    @JsonProperty(required = true)
    private Amount amount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime occurrence_date;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime value_date;


    private BeneficiaryEnum beneficiary;




    private QualityEnum quality;
    private ArrayList<TransactionMetadata> metadata;
    private ArrayList<ReceivableEvent> events;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;



    private ArrayList<Document> documents;




    @JsonIgnore
    public IdDenormalizer idDenormalizer;


    private String normalizeCustomerId(String oldId) {
        return oldId != null ? this.idDenormalizer.customer(oldId) : null;
    }

    private String normalizeFileId(String oldId) {
        return oldId != null ? this.idDenormalizer.file(oldId) : null;
    }





    public String getYour_reference() {
        return your_reference;
    }

    public void setYour_reference(String your_reference) {
        this.your_reference = your_reference;
    }

    public String getCustomer() {
        return this.normalizeCustomerId(customer);
    }

    public void setCustomer(String customer) {
        this.customer = this.normalizeCustomerId(customer);
    }

    public String getFile() {
        return this.normalizeFileId(file);
    }

    public void setFile(String file) {
        this.file = this.normalizeFileId(file);
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public LocalDateTime getValue_date() {
        return value_date;
    }

    public void setValue_date(LocalDateTime value_date) {
        this.value_date = value_date;
    }

    public LocalDateTime getOccurrence_date() {
        return occurrence_date;
    }

    public void setOccurrence_date(LocalDateTime occurrence_date) {
        this.occurrence_date = occurrence_date;
    }


    public QualityEnum getQuality() {
        return quality;
    }

    @JsonGetter("quality")
    public String getQualityJson() {
        return (quality != null) ? quality.toString().toLowerCase() : null;
    }

    public void setQuality(QualityEnum quality) {
        this.quality = quality;
    }

    @JsonSetter("quality")
    public QualityEnum setTypeEnum(String value) {
        this.quality = QualityEnum.fromValue(value);
        return this.quality;
    }


    public ArrayList<TransactionMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<TransactionMetadata> metadata) {
        this.metadata = metadata;
    }

    public ArrayList<ReceivableEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<ReceivableEvent> events) {
        this.events = events;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }



    public BeneficiaryEnum getBeneficiary() {
        return beneficiary;
    }

    @JsonGetter("beneficiary")
    public String getBeneficiaryJSON() {
        return (beneficiary != null) ? beneficiary.toString().toLowerCase() : null;
    }

    public void setBeneficiary(BeneficiaryEnum beneficiary) {
        this.beneficiary = beneficiary;
    }

    @JsonSetter("beneficiary")
    public BeneficiaryEnum setBeneficiaryJSON(String value) {
        this.beneficiary = BeneficiaryEnum.fromValue(value);
        return this.beneficiary;
    }



    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }


}
