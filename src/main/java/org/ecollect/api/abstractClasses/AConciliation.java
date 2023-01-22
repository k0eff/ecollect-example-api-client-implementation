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



@JsonIgnoreProperties("acting_as")
public class AConciliation {


    private String your_reference;

    @JsonProperty(required = true)
    private Amount amount;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime value_date;
    private ArrayList<PaymentCreditMetadata> metadata;

    private String customer;
    private String claim;
    private String charge;
    private String invoice;
    private String account;
    private String file;

    private ArrayList<MatchedConciliation> matched_entries;
    private ConciliationStatusEnum status;
    private String reason;

    private ArrayList<Document> documents;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;




    @JsonIgnore
    public IdDenormalizer idDenormalizer;





    public String getYour_reference() {
        return your_reference;
    }

    public void setYour_reference(String your_reference) {
        this.your_reference = your_reference;
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

    public ArrayList<PaymentCreditMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<PaymentCreditMetadata> metadata) {
        this.metadata = metadata;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getClaim() {
        return claim;
    }

    public void setClaim(String claim) {
        this.claim = claim;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }



    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }




    public ConciliationStatusEnum getConciliationStatus() {
        return status;
    }

    @JsonGetter("status")
    public String getConciliationStatusJson() {
        return (status != null) ? status.toString().toLowerCase() : null;
    }

    public void setConciliationStatus(ConciliationStatusEnum status) {
        this.status = status;
    }

    @JsonSetter("status")
    public ConciliationStatusEnum setConciliationStatusEnumJson(String value) {
        this.status = ConciliationStatusEnum.fromValue(value);
        return this.status;
    }


    public ArrayList<MatchedConciliation> getMatched_entries() {
        return matched_entries;
    }

    public void setMatched_entries(ArrayList<MatchedConciliation> matched_entries) {
        this.matched_entries = matched_entries;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
