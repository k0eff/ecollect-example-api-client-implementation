package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigDecimal;

public class Tax {


    private TaxTypeEnum type;
    private BigDecimal percent;




    public enum TaxTypeEnum {
        VAT("VAT"),
        SALES_TAX("Sales Tax");

        private String value;

        TaxTypeEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static TaxTypeEnum fromValue(String value) {
            for (TaxTypeEnum b : TaxTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }








    @JsonGetter("type")
    public String getTaxTypeJson() {
        return (type != null) ? type.toString() : null;
    }

    public TaxTypeEnum getType() {
        return type;
    }

    @JsonSetter("type")
    public TaxTypeEnum setTaxTypeEnumJson(String value) {
        this.type = TaxTypeEnum.fromValue(value);
        return this.type;
    }

    public void setType(TaxTypeEnum type) {
        this.type = type;
    }




    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }
}
