package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.ecollect.api.abstractClasses.AEvent;

public class OrderEvent extends AEvent {



    private OrderEvent.TypeEnum type;




    public enum TypeEnum {
        REMINDER("reminder") ,
        CONTRAVENTION("contravention") ,
        CONTRACT("contract"),
        PURCHASE("purchase"),
        DOWNLOAD("download"),
        SUBSCRIPTION("subscription"),
        RENEWAL("renewal"),
        ORDER("order"),
        BOOKING("booking"),
        DELIVERY("delivery"),
        TICKET("ticket"),
        POSTING("posting"),
        CHARGEBACK("chargeback"),
        MAILING("mailing"),
        STATE_CHANGE("state_change");



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
        this.type = OrderEvent.TypeEnum.fromValue(value);
        return this.type;
    }









}
