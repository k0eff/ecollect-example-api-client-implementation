package org.ecollect.api.classes;


public enum ContractualItemEnum {
        MEDICAL_CARE("medical_care"),

        SERVICE_AGREEMENT("service_agreement"),

        LOAN_REPAYMENT("loan_repayment"),

        TRADESMENS_SERVICES("tradesmens_services"),

        PURCHASE_AGREEMENT("purchase_agreement"),

        LEASING_AGREEMENT("leasing_agreement"),

        RENTAL_AGREEMENT("rental_agreement"),

        ORDER_OF_GOODS("order_of_goods"),

        CONTRACT_FOR_WORK("contract_for_work"),

        INTEREST("interest"),

        SHIPPING_SERVICES("shipping_services"),

        TRAFFIC_CONTRAVENTION("traffic_contravention");

        private String value;

        ContractualItemEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static ContractualItemEnum fromValue(String value) {
            for (ContractualItemEnum b : ContractualItemEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

