package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class TransactionMetadata {

    @JsonProperty(required = true)
    private String value;
    @JsonProperty(required = true)
    private TypeEnum type;


    public enum TypeEnum {
        INVOICE_REFERENCE("invoice:reference"),
        INVOICE_ADDRESS_LINE_1("invoice:address_line_1"),
        INVOICE_ADDRESS_LINE_2("invoice:address_line_2"),
        INVOICE_ADDRESS_CITY("invoice:address_city"),
        INVOICE_ADDRESS_COUNTRY("invoice:address_country"),
        INVOICE_ADDRESS_ZIP("invoice:address_zip"),
        INVOICE_AMOUNT("invoice:amount"),

        TRANSACTION_REFERENCE("transaction:reference"),
        CONTRACT_REFERENCE("contract:reference"),
        REPORT_REFERENCE("report:reference"),
        ACCOUNT_REFERENCE("account:reference"),

        CLAIM_HOLDER_ADDRESS_COUNTRY("claim:holder:address:country"),
        CLAIM_HOLDER_ADDRESS_CITY("claim:holder:address:city"),
        CLAIM_HOLDER_NAME("claim:holder:name"),

        DUNNING_LEVEL("dunning:level"),

        CONTRAVENTION_REFERENCE("contravention:reference"),
        CONTRAVENTION_LEGAL_BASIS("contravention:legal_basis"),
        CONTRAVENTION_LOCATION("contravention:location"),
        CONTRAVENTION_TIME("contravention:time"),

        CONTRAVENTION_SPEED_REGISTERED("contravention:speed:registered"),
        CONTRAVENTION_SPEED_LIMIT("contravention:speed:limit"),
        CONTRAVENTION_SPEED_EXCESS("contravention:speed:excess"),
        CONTRAVENTION_CONTRAVENER_NAME("contravention:contravener:name"),

        VEHICLE_LICENSE_PLATE("vehicle:license_plate"),
        VEHICLE_MAKE("vehicle:make"),
        VEHICLE_OWNER("vehicle:owner"),
        VEHICLE_GROUP("vehicle:group"),

        REMINDER_DATE("reminder:date"),
        REMINDER_AMOUNT_VALUE("reminder:amount:value"),
        REMINDER_AMOUNT_CURRENCY("reminder:amount:currency"),

        NOTIFICATION_DATE("notification:date"),

        CONFIRMATION_EMAIL("confirmation:email"),

        ORDER_RATE("order:rate"),
        ORDER_DEPOSIT("order:deposit"),
        ORDER_CASHBACK("order:cashback"),
        ORDER_PREPAID("order:prepaid"),
        ORDER_TYPE("order:type"),

        RENTAL_PICKUP_DATE("rental:pickup:date"),
        RENTAL_PICKUP_TIME("rental:pickup:time"),
        RENTAL_PICKUP_CITY("rental:pickup:city"),

        RENTAL_DROPOFF_DATE("rental:dropoff:date"),
        RENTAL_DROPOFF_TIME("rental:dropoff:time"),
        RENTAL_DROPOFF_CITY("rental:dropoff:city"),

        PAYMENT_METHOD("payment:method"),

        IMPORT_CSV_FILENAME("import:csv:filename"),

        COMMENTS("comments"),

        TAX_ID("tax:id"),

        LANGUAGE("language")

        ;



        private String value;

        TypeEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeEnum getType() {
        return type;
    }

    @JsonGetter("type")
    public String getTypeJson() {
        return (type != null) ? type.toString().toLowerCase() : null;
    }


    public void setType(TypeEnum type) {
        this.type = type;
    }

    @JsonSetter("type")
    public TypeEnum setTypeEnumJson(String value) {
        this.type = TypeEnum.fromValue(value);
        return this.type;
    }


}
