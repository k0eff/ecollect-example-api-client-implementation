package org.ecollect.api.classes;

public enum PspTypeEnum {
    DIRECT_PAYMENT("direct_payment"),
    WIRE_TRANSFER("wire_transfer"),
    EXTERNAL_PAYMENT_PROVIDER("external_payment_provider"),
    ACCOUNT("account");

    private String value;

    PspTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static PspTypeEnum fromValue(String value) {
        for (PspTypeEnum b : PspTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}