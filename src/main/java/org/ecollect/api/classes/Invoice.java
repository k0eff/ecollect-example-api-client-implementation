package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;
import org.ecollect.api.utils.IdDenormalizer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;



public class Invoice {


    private String id;

    @JsonProperty(required = true)
    private String customer; // property is denormalized

    private String your_reference;

    @JsonProperty(required = true)
    private Currency currency;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime occurrence_date;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime due_date;

    private ArrayList<InvoiceItem> items;

    private ArrayList<TransactionMetadata> metadata;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;


    private ArrayList<Document> documents;



    private IdDenormalizer idDenormalizer;

    @JsonCreator
    public Invoice() throws Exception {
        this.idDenormalizer = IdDenormalizer.getInstance();
    }




    private String normalizeCustomerId(String oldId) {
        return oldId != null ? this.idDenormalizer.customer(oldId) : null;
    }

    private String normalizeInvoiceId(String oldId) {
        return oldId != null ? this.idDenormalizer.invoice(oldId) : null;
    }




    public String getId() {
        return this.normalizeInvoiceId(id);
    }

    public void setId(String id) {
        this.id = this.normalizeInvoiceId(id);
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


    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getOccurrence_date() {
        return occurrence_date;
    }

    public void setOccurrence_date(LocalDateTime occurrence_date) {
        this.occurrence_date = occurrence_date;
    }



    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }


    public ArrayList<TransactionMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<TransactionMetadata> metadata) {
        this.metadata = metadata;
    }


    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public ArrayList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
