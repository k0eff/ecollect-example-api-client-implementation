package org.ecollect.api.classes;

public enum PaymentCreditTypeEnum {
    INVOICE_REF("invoice:reference"),
    TRANSACTION_REF("transaction:reference"),
    CONTRACT_REF("contract:reference"),
    REPORT_REF("report:reference"),
    CONTRAVENTION_REF("contravention:reference");

    private String value;

    PaymentCreditTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static PaymentCreditTypeEnum fromValue(String value) {
        for (PaymentCreditTypeEnum b : PaymentCreditTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
