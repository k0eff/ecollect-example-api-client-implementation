package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.math.BigDecimal;
import java.math.BigInteger;

public class InvoiceItem {


    private String subject_matter;

    private ContractualItemEnum contractual_item;

    private UnitTypeEnum unit_type;

    private BigInteger quantity;
    private BigDecimal unit_price;
    private Tax tax;









    public enum UnitTypeEnum {
        QUANTITY("quantity"),
        HOUR("hour"),
        AMOUNT("amount");

        private String value;

        UnitTypeEnum(String value) {
            this.value = value;
        }


        public String toString() {
            return String.valueOf(value);
        }


        public static UnitTypeEnum fromValue(String value) {
            for (UnitTypeEnum b : UnitTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }











    public String getSubject_matter() {
        return subject_matter;
    }

    public void setSubject_matter(String subject_matter) {
        this.subject_matter = subject_matter;
    }




    @JsonGetter("contractual_item")
    public String getContractualItemJson() {
        return (contractual_item != null) ? contractual_item.toString().toLowerCase() : null;
    }

    public ContractualItemEnum getContractual_item() {
        return contractual_item;
    }

    @JsonSetter("contractual_item")
    public ContractualItemEnum setgetContractualItemJson(String value) {
        this.contractual_item = ContractualItemEnum.fromValue(value);
        return this.contractual_item;
    }

    public void setContractual_item(ContractualItemEnum contractual_item) {
        this.contractual_item = contractual_item;
    }






    @JsonGetter("unit_type")
    public String getUnitTypeJson() {
        return (unit_type != null) ? unit_type.toString().toLowerCase() : null;
    }

    public UnitTypeEnum getUnit_type() {
        return unit_type;
    }

    @JsonSetter("unit_type")
    public UnitTypeEnum setUnitTypeEnumJson(String value) {
        this.unit_type = UnitTypeEnum.fromValue(value);
        return this.unit_type;
    }

    public void setUnit_type(UnitTypeEnum unit_type) {
        this.unit_type = unit_type;
    }


    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }
}
