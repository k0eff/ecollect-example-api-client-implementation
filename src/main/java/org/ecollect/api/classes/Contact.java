package org.ecollect.api.classes;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Contact {

    @JsonProperty(required = true)
    private TypeEnum type;

    @JsonProperty(required = true)
    private String value;

    public enum TypeEnum {
        EMAIL("email"),

        PHONE("phone"),

        CELLPHONE("cellphone"),

        FAX("fax"),

        SKYPE("skype"),

        FACEBOOK_MESSENGER("facebook_messenger"),

        IMESSAGE("imessage"),

        WHATSAPP("whatsapp"),

        VIBER("viber"),

        FACEBOOK("facebook"),

        GOOGLEPLUS("google+"),

        TWITTER("twitter"),

        LINKEDIN("linkedin"),

        XING("xing"),

        BADOO("badoo"),

        SOCIAL_VARIOUS("social_various"),

        WEBSITE_URL("website_url"),

        WEB_VARIOUS("web_various");

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
    public TypeEnum setTypeEnum(String value) {
        this.type = TypeEnum.fromValue(value);
        return this.type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
