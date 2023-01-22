package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ecollect.api.abstractClasses.AEntityCommon;
import org.ecollect.api.interfaces.IEntity;


@JsonIgnoreProperties(value = {"documents", "relations"})
public class EntityPerson extends AEntityCommon implements IEntity {

    @JsonIgnore
    private final String entityType = "Person";

    @JsonProperty(required = true)
    private Person person;

    public EntityPerson() {}

    public EntityPerson(Person person) {
        this.person = person;
    }


    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }

    @Override
    public Organisation getOrganisation() {
        return null;
    }
}
