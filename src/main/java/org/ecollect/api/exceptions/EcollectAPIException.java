package org.ecollect.api.exceptions;

import org.ecollect.api.exceptions.EcollectAPIExceptionModel.classes.ApiErrorResponse;

public class EcollectAPIException extends Exception {

    private final String defaultErrorMsg = "Ecollect General API Error";

    private ApiErrorResponse apiErrorResponse;
    private String message;
    private String jsonRaw;

    public EcollectAPIException(ApiErrorResponse errResp, String jsonRaw) {

        // TODO: handle 4xx, 5xx exceptions
        try {
            this.apiErrorResponse = errResp;
            this.jsonRaw = jsonRaw;
            this.message = errResp.getError().getMessage();
        } catch (Exception e) {
            message = defaultErrorMsg;
        }
    }

    public EcollectAPIException(String jsonRaw) {
        this.jsonRaw = jsonRaw;
        this.message = defaultErrorMsg;
    }


    public EcollectAPIException(String jsonRaw, String errorMsg) {
        this.jsonRaw = jsonRaw;
        this.message = errorMsg;
    }


    public ApiErrorResponse getApiErrorResponse() {
        return apiErrorResponse;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getJsonRaw() {
        return jsonRaw;
    }
    public void setJsonRaw(String jsonRaw) {
        this.jsonRaw = jsonRaw;
    }

    public void setApiErrorResponse(ApiErrorResponse apiErrorResponse) {
        this.apiErrorResponse = apiErrorResponse;
        this.message = apiErrorResponse.getError().getMessage();
    }

}
