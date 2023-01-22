package org.ecollect.api.classes;

public enum StageEnum {
    INVOICE("invoice"),
    COLLECTION("collection");

    private String value;

    StageEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static StageEnum fromValue(String value) {
        for (StageEnum b : StageEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}