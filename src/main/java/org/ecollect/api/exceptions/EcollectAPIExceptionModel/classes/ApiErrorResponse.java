package org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponse {

    private ApiErrorRespError error = null;

    public ApiErrorRespError getError() {
        return error;
    }

    public void setError(ApiErrorRespError error) {
        this.error = error;
    }


    public ApiErrorResponse() {}

}
