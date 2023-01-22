package org.ecollect.api.classes;

public enum ConciliationStatusEnum {
    NEW("new"),
    MATCHED("matched"),
    REJECTED("rejected");

    private String value;

    ConciliationStatusEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static ConciliationStatusEnum fromValue(String value) {
        for (ConciliationStatusEnum b : ConciliationStatusEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}