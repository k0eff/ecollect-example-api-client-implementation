package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public enum PaymentTypeEnum {
    PAYMENT("payment"),
    CREDIT("credit");

    private String value;

    PaymentTypeEnum(String value) {
        this.value = value;
    }


    public String toString() {
        return String.valueOf(value);
    }


    public static PaymentTypeEnum fromValue(String value) {
        for (PaymentTypeEnum b : PaymentTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}