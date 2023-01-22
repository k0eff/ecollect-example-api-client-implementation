package org.ecollect.api.classes;

public enum AdditionalChargeTypeEnum {
    REMINDER_FEE("reminder_fee"),
    PROCESSING_FEE("processing_fee"),
    CANCELLATION_FEE("cancellation_fee"),
    CONVENIENCE_FEE("convenience_fee"),
    ADVISORY_FEE("advisory_fee"),
    HANDLING_FEE("handling_fee"),
    INSURANCE_FEE("insurance_fee"),
    LEGAL_FEE("legal_fee"),
    DELIVERY_CHARGE("delivery_charge"),
    SERVICE_CHARGE("service_charge"),
    BANK_CHARGE("bank_charge"),
    DATA_PREPARATION("data_preparation"),
    COMMUNICATION_COST("communication_cost"),
    EXPENSES("expenses"),
    INTEREST("interest");

    private String value;

    AdditionalChargeTypeEnum(String value) {
        this.value = value;
    }

    public String toString() {
        return String.valueOf(value);
    }

    public static AdditionalChargeTypeEnum fromValue(String value) {
        for (AdditionalChargeTypeEnum b : AdditionalChargeTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
