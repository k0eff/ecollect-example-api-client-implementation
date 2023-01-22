package org.ecollect.api.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.ecollect.api.classes.EntityOrganisation;
import org.ecollect.api.classes.EntityPerson;
import org.ecollect.api.interfaces.IEntity;

import java.io.IOException;

public class EntityDeserializer extends StdDeserializer {


    static final String PERSON = "person";
    static final String ORGANISATION = "organisation";


    public EntityDeserializer() {
        this(null);
    }


    public EntityDeserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public IEntity deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = p.getCodec().readTree(p);
        JsonParser nodeParser = node.traverse();
        nodeParser.setCodec(p.getCodec());

        JsonNode organisation = node.get(EntityDeserializer.ORGANISATION);
        if (organisation != null) return nodeParser.readValueAs(EntityOrganisation.class);

        JsonNode person = node.get(EntityDeserializer.PERSON);
        if (person != null) return nodeParser.readValueAs(EntityPerson.class);


        return null;
    }

}