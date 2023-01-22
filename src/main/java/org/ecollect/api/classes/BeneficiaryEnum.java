package org.ecollect.api.classes;

public enum BeneficiaryEnum {
    ACCOUNT("account"),
    ECOLLECT("ecollect"),
    THIRD_PARTY("third_party");

    private String value;

    BeneficiaryEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static BeneficiaryEnum fromValue(String value) {
        for (BeneficiaryEnum b : BeneficiaryEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}