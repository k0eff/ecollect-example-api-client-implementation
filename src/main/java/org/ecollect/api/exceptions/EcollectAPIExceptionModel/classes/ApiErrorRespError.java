package org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorRespError {
    private String code;
    private String message;
    private ApiErrorRespData data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiErrorRespData getData() {
        return data;
    }

    public void setData(ApiErrorRespData data) {
        this.data = data;
    }


    public ApiErrorRespError() {}
}
