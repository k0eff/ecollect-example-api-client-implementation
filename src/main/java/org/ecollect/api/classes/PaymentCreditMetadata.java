package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class PaymentCreditMetadata {

    private String value;
    private PaymentCreditTypeEnum type;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public PaymentCreditTypeEnum getType() {
        return type;
    }

    @JsonGetter("type")
    public String getTypeJSON() {
        return (type != null) ? type.toString().toLowerCase() : null;
    }

    public void setType(PaymentCreditTypeEnum paymentCreditType) {
        this.type = paymentCreditType;
    }

    @JsonSetter("type")
    public PaymentCreditTypeEnum setTypeJSON(String value) {
        this.type = PaymentCreditTypeEnum.fromValue(value);
        return this.type;
    }






}
