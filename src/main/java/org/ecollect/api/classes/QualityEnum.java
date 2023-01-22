package org.ecollect.api.classes;

public enum QualityEnum {
    REGULAR("regular"),
    SPECIAL("special"),
    SECOND_PLACEMENT("second_placement"),
    THIRD_PLACEMENT("third_placement");

    private String value;

    QualityEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static QualityEnum fromValue(String value) {
        for (QualityEnum b : QualityEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

}
