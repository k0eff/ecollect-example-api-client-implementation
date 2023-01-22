package org.ecollect.api.classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.deserializers.NotificationV2Deserializer;
import org.ecollect.api.interfaces.INotificationPayload;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;


@JsonDeserialize(using = NotificationV2Deserializer.class)
public class NotificationV2 {

    private String id;
    private String aggId;
    private String type;
    private Regarding regarding;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;
    private String version;

    private INotificationPayload payload;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAggId() {
        return aggId;
    }

    public void setAggId(String aggId) {
        this.aggId = aggId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Regarding getRegarding() {
        return regarding;
    }

    public void setRegarding(Regarding regarding) {
        this.regarding = regarding;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public INotificationPayload getPayload() {
        return payload;
    }

    public void setPayload(INotificationPayload payload) {
        this.payload = payload;
    }




}
