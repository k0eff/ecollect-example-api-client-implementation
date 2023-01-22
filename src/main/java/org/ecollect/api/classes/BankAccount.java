package org.ecollect.api.classes;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.BankAccountObjectDeserializer;
import org.ecollect.api.interfaces.IBankAccount;
import org.ecollect.api.serializers.BankAccountObjectSerializer;


@JsonSerialize(using = BankAccountObjectSerializer.class)
@JsonDeserialize(using = BankAccountObjectDeserializer.class)
public class BankAccount implements IBankAccount {

    private bankAccountTypeEnum bankAccountType;
    private BankAccountAccNumber bankAccountAccNumber;
    private BankAccountIBAN bankAccountIBAN;



    public enum bankAccountTypeEnum {
        IBAN("IBAN"),

        AccNumber("AccNumber");

        private String value;

        bankAccountTypeEnum(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(this.value).toLowerCase();
        }

        public static bankAccountTypeEnum fromValue(String value) {
            for (bankAccountTypeEnum b : bankAccountTypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }




    @Override
    public bankAccountTypeEnum getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(bankAccountTypeEnum bankAccountType) {
        this.bankAccountType = bankAccountType;
    }






    @Override
    public BankAccountAccNumber getBankAccountAccNumber() {
        return bankAccountAccNumber;
    }

    public void setBankAccountAccNumber(BankAccountAccNumber bankAccountAccNumber) {
        this.bankAccountAccNumber = bankAccountAccNumber;
    }

    @Override
    public BankAccountIBAN getBankAccountIBAN() {
        return bankAccountIBAN;
    }

    public void setBankAccountIBAN(BankAccountIBAN bankAccountIBAN) {
        this.bankAccountIBAN = bankAccountIBAN;
    }
}
