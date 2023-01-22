package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderMetadata {

    @JsonIgnore
    private final String regex = "^other:[a-zA-Z0-9_-]+$";


    private String value;
    private String type;


    public enum PredefinedTypeEnum {
        AIRLINE_REFERENCE("airline:reference"),
        AIRPORT_DEPARTURE_REFERENCE("airport:departure:reference"),
        AIRPORT_DESTINATION_REFERENCE("airport:destination:reference"),
        BOOKING_DATE("booking:date"),
        BOOKING_REFERENCE("booking:reference"),
        CHARGE_REFERENCE("charge:reference"),
        PAYMENT_METHOD("payment:method"),
        PAYMENT_NAME("payment:name"),
        PAYMENT_REFERENCE("payment:reference"),
        PAYMENT_TYPE("payment:type"),
        FLIGHT_INWARD_DATE("flight:inward:date"),
        FLIGHT_OUTWARD_DATE("flight:outward:date"),
        INVOICE_DATE("invoice:date"),
        INVOICE_REFERENCE("invoice:reference"),
        ITEM_AMOUNT("item:amount"),
        ITEM_PACKAGE_NAME("item:package:name"),
        ITEM_PACKAGE_TYPE("item:package:type"),
        ITEM_REFERENCE("item:reference"),
        MERCHANT_ADDRESS("merchant:address"),
        MERCHANT_ADDRESS_CITY("merchant:address_city"),
        MERCHANT_ADDRESS_COUNTRY("merchant:address_country"),
        MERCHANT_ADDRESS_ZIP("merchant:address_zip"),
        MERCHANT_COMPANY_NAME("merchant:company_name"),
        MERCHANT_COMPANY_TYPE("merchant:company_type"),
        MERCHANT_REPRESENTATIVE_FIRSTNAME("merchant:representative_firstname"),
        MERCHANT_REPRESENTATIVE_SECONDNAME("merchant:representative_secondname"),
        MERCHANT_WEBSITE_URL("merchant:website_url"),
        ORDER_AMOUNT("order:amount"),
        ORDER_DATE("order:date"),
        ORDER_QUANTITY("order:quantity"),
        ORDER_REFERENCE("order:reference"),
        POS_COUNTRY("pos:country"),
        QUALITY_PREVIOUS_COLLECTIONS_SUCCESSFUL("quality:previous_collections:successful"),
        QUALITY_PREVIOUS_COLLECTIONS_TOTAL("quality:previous_collections:total"),
        QUALITY_PREVIOUS_PAYMENTS_DEFAULTS("quality:previous_payments:defaults"),
        QUALITY_PREVIOUS_PAYMENTS_QUANTITY("quality:previous_payments:quantity"),
        QUALITY_REMINDER_QUANTITY("quality:reminder:quantity"),
        QUALITY_RENEWALS_QUANTITY("quality:renewals:quantity"),
        SHOP_REFERENCE("shop:reference"),
        SHOP_WEBSITE_URL("shop:website_url"),
        SUBSCRIPTION_PERIOD("subscription:period"),
        TICKET_QUANTITY("ticket:quantity"),
        TICKET_TYPE("ticket:type"),
        TRANSACTION_REFERENCE("transaction:reference"),
        TRANSACTION_SOURCE_IP_ADDRESS("transaction:source_ip_address"),
        VEHICLE_LICENSE_PLATE("vehicle:license_plate"),
        VEHICLE_MAKE("vehicle:make"),
        REMINDER_CLAIM_GROSS("reminder:claim_gross"),
        PRINCIPAL_CLAIM_REDUCTION("principal_claim:reduction"),
        AMOUNT_FARE("amount_fare"),
        AMOUNT_TAX("amount_tax"),
        HEALTH_INSURANCE("health_insurance"),
        LUGGAGE_INSURANCE("luggage_insurance"),
        AIRFARE("airfare"),
        DELIVERY_CHARGE("delivery_charge"),
        INSURANCE_FEE("insurance_fee"),
        ADVISORY_FEE("advisory_fee"),
        REBOOKING_FEE("rebooking_fee"),
        HANDLING_FEE("handling_fee"),
        SERVICE_PACKAGE("service_package"),
        CANCELLATION_FEE("cancellation_fee"),
        CONVENIENCE_FEE("convenience_fee"),
        SERVICE_CHARGE("service_charge"),
        CANCELLATION_FEE_GOODWILL("cancellation_fee:goodwill"),
        CANCELLATION_FEE_AIRFARE("cancellation_fee:airfare"),
        CHARGEBACK_SERVICE_PACKAGE("chargeback:service_package"),
        CHARGEBACK_CONVENIENCE_FEE("chargeback:convenience_fee"),
        CHARGEBACK_CANCELLATION_FEE("chargeback:cancellation_fee"),
        CHARGEBACK_CANCELLATION_FEE_AIRFARE("chargeback:cancellation_fee_airfare"),
        CHARGEBACK_ADVISORY_FEE("chargeback:advisory_fee"),
        CHARGEBACK_REBOOKING_FEE("chargeback:rebooking_fee"),
        CHARGEBACK_HANDLING_FEE("chargeback:handling_fee"),
        CHARGEBACK_HEALTH_INSURANCE("chargeback:health_insurance"),
        CHARGEBACK_LUGGAGE_INSURANCE("chargeback:luggage_insurance"),
        CHARGEBACK_SERVICE_CHARGE("chargeback:service_charge"),
        CHARGEBACK_AIRFARE("chargeback:airfare"),
        CHARGEBACK_GOODWILL_CANCELLATION_FEE("chargeback:goodwill_cancellation_fee"),
        CHARGEBACK_DELIVERY_CHARGE("chargeback:delivery_charge"),
        CHARGEBACK_INSURANCE_FEE_CLEVERFLY("chargeback:insurance_fee_cleverfly");
        
        
        
        
        

        private String value;

        PredefinedTypeEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static String fromValue(String value) {
            for (PredefinedTypeEnum b : PredefinedTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b.value;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }


    public String getType() {
        return type;
    }

    @JsonGetter("type")
    public String getTypeJson() {
        return (type != null) ? type.toLowerCase() : null;
    }

    public void setType(PredefinedTypeEnum type) {
        this.type = type.toString();
    }

    public void setType(String type) {
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(type);
        if (!m.find()) {
            throw new IllegalArgumentException("OrderMetadata: Cannot match metadata type. Please use the following regex format: " + this.regex);
        }
        this.type = type;
    }

    @JsonSetter("type")
    public void setTypeEnumJson(String value) {

        try {
            this.type = PredefinedTypeEnum.fromValue(value);
        } catch (IllegalArgumentException e) {
            try {
                this.setType(value);
            } catch (IllegalArgumentException eInner) {
                throw new IllegalArgumentException("OrderMetadata: cannot set type through JSON setter. Please check the format");
            }
        }
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
