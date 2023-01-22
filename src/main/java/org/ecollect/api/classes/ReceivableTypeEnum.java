package org.ecollect.api.classes;

public enum ReceivableTypeEnum {
    PAYMENT("payment"),
    CREDIT("credit"),
    CHARGE("charge"),
    CLAIM("claim"),
    INVOICE("invoice");

    private String value;

    ReceivableTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static ReceivableTypeEnum fromValue(String value) {
        for (ReceivableTypeEnum b : ReceivableTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
