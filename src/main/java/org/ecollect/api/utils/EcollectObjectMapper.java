package org.ecollect.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes.ApiErrorResponse;


public class EcollectObjectMapper {

    private ObjectMapper objectMapper;

    public EcollectObjectMapper(ObjectMapper om) {
        this.objectMapper = om;
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        this.objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
    }


    public String writeValueAsString(Object value) throws JsonProcessingException {
        return this.objectMapper.writeValueAsString(value);
    }


    public <T> T readValue(String rawResponse, Class<T> valueType) throws EcollectAPIException {
        EcollectAPIException ecollectAPIException = new EcollectAPIException(rawResponse);
        try {
            return this.objectMapper.readValue(rawResponse, valueType);
        } catch (UnrecognizedPropertyException e) {
            if (e.getPropertyName().equals("error")) { // if this is an eCollect error, it MUST contain an "error" property
                try {
                    ApiErrorResponse aer = this.objectMapper.readValue(rawResponse, ApiErrorResponse.class);
                    ecollectAPIException.setApiErrorResponse(aer);
                } catch (JsonProcessingException ex) {
                    // do nothing
                }
            }
            throw ecollectAPIException;
        } catch (Exception e) {
            throw ecollectAPIException;
        }
    }
}
