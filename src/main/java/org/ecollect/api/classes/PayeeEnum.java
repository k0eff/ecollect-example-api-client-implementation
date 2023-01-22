package org.ecollect.api.classes;


public enum PayeeEnum {
    ACCOUNT("account"),
    ECOLLECT("ecollect"),
    THIRD_PARTY("third_party");

    private String value;

    PayeeEnum(String value) {
        this.value = value;
    }


    public String toString() {
        return String.valueOf(value);
    }


    public static PayeeEnum fromValue(String value) {
        for (PayeeEnum b : PayeeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}

