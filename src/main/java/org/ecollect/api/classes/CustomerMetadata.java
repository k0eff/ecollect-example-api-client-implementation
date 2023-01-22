package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;


public class CustomerMetadata {


    @JsonProperty(required = true)
    private TypeEnum type;

    @JsonProperty(required = true)
    private String value;
    public enum TypeEnum {
        REFERENCE("user:reference"),

        NAME("user:name"),

        IP("user:ip"),

        LANGUAGE("user:language");

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

    public CustomerMetadata setType(TypeEnum type) {
        this.type = type;
        return this;
    }

    public String getValue() {
        return value;
    }

    public CustomerMetadata setValue(String value) {
        this.value = value;
        return this;
    }


    @JsonSetter("type")
    public TypeEnum setValueJson(String value) {
        this.type = TypeEnum.fromValue(value);
        return this.type;
    }

}