package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.EntityDeserializer;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.IEntity;
import org.ecollect.api.serializers.LocalDateTimeSerializer;
import org.ecollect.api.utils.IdDenormalizer;

import java.time.LocalDateTime;
import java.util.ArrayList;



public class Customer {
    private String id;
    private String your_reference;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;

    @JsonDeserialize(using = EntityDeserializer.class)
    @JsonProperty(required = true)
    private IEntity entity;
    private ArrayList<CustomerMetadata> metadata;
    private ArrayList<CustomerEvent> events;
    private ArrayList<Document> documents;



    @JsonIgnore
    private IdDenormalizer idDenormalizer;

    @JsonCreator
    public Customer() throws Exception {
        this.idDenormalizer = IdDenormalizer.getInstance();
    }


    public String normalizeCustomerId(String oldId) {
        return this.idDenormalizer.customer(oldId);
    }






    public String getId() {
        return normalizeCustomerId(id);
    }

    public Customer setId(String id) {
        this.id = normalizeCustomerId(id);
        return this;
    }

    public String getYour_reference() {
        return your_reference;
    }

    public Customer setYour_reference(String your_reference) {
        this.your_reference = your_reference;
        return this;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public IEntity getEntity() {
        return entity;
    }

    public Customer setEntity(IEntity entity) {
        this.entity = entity;
        return this;
    }

    public ArrayList<CustomerMetadata> getMetadata() {
        return metadata;
    }

    public Customer setMetadata(ArrayList<CustomerMetadata> metadata) {
        this.metadata = metadata;
        return this;
    }

    public ArrayList<CustomerEvent> getEvents() {
        return events;
    }

    public Customer setEvents(ArrayList<CustomerEvent> events) {
        this.events = events;
        return this;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
