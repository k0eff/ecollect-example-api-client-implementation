package org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.ecollect.api.exceptions.EcollectAPIExceptionModel.deserializers.ApiErrRespFieldDeserializer;

import java.util.ArrayList;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorRespData {

    private String message;

    @JsonDeserialize(using = ApiErrRespFieldDeserializer.class)
    private ArrayList<ApiErrRespField> fields;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ApiErrRespField> getFields() {
        return fields;
    }

    public void setFields(ArrayList<ApiErrRespField> fields) {
        this.fields = fields;
    }


    public ApiErrorRespData() {}
}
