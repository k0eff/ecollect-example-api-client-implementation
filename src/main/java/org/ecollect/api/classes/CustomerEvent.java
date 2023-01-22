package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.ecollect.api.abstractClasses.AEvent;

public class CustomerEvent extends AEvent {

    @JsonProperty(required = true)
    private TypeEnum type;




    public enum TypeEnum {
        REGISTRATION("registration"),

        LOGIN("login"),

        INSOLVENCY("insolvency");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        @Override
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

    public CustomerEvent setType(TypeEnum type) {
        this.type = type;
        return this;
    }


    @JsonSetter("type")
    public CustomerEvent.TypeEnum setValueJson(String value) {
        this.type = CustomerEvent.TypeEnum.fromValue(value);
        return this.type;
    }


}
