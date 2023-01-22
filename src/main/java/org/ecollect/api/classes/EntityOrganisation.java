package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ecollect.api.abstractClasses.AEntityCommon;
import org.ecollect.api.interfaces.IEntity;

@JsonIgnoreProperties(value = { "relations"})
public class EntityOrganisation extends AEntityCommon implements IEntity {

    @JsonIgnore
    private final String entityType = "Organisation";

    @JsonProperty(required = true)
    private Organisation organisation;


    public Organisation getOrganisation() {
        return organisation;
    }

    public EntityOrganisation setOrganisation(Organisation organisation) {
        this.organisation = organisation;
        return this;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }


    @Override
    public Person getPerson() {
        return null;
    }
}
