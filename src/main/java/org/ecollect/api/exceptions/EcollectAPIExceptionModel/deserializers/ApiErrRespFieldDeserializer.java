package org.ecollect.api.exceptions.EcollectAPIExceptionModel.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes.ApiErrRespField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ApiErrRespFieldDeserializer extends StdDeserializer {


    public static final String IBAN = "iban";

    /**
     * Deserializer which adds each error object name in an ArrayList
     * TODO: extend the object to include further data
     */

    public ApiErrRespFieldDeserializer(Class<?> vc) {
        super(vc);
    }

    public ApiErrRespFieldDeserializer() {
            this(null);
        }





    @Override
    public ArrayList<ApiErrRespField> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        ArrayList<ApiErrRespField> fields = new ArrayList<>(1);
        Iterator<String> is = node.fieldNames();

        while (is.hasNext()) {
            ApiErrRespField singleField = new ApiErrRespField();
            String fieldName = is.next();
            singleField.setName(fieldName);
            fields.add(singleField);
        }
        return fields;
    }


}
