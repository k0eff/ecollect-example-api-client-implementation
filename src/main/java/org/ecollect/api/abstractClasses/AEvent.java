package org.ecollect.api.abstractClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public abstract class AEvent {

    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime occurrence;

    private String your_reference;
    private String description;
    private String location;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(LocalDateTime occurrence) {
        this.occurrence = occurrence;
    }

    public String getYour_reference() {
        return your_reference;
    }

    public void setYour_reference(String your_reference) {
        this.your_reference = your_reference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }







}
