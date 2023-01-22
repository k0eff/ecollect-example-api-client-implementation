package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.IBankAccountType;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class BankAccountIBAN implements IBankAccountType {

    @JsonIgnore
    private final BankAccount.bankAccountTypeEnum bankAccountType = BankAccount.bankAccountTypeEnum.IBAN;


    @JsonProperty(required = true)
    private String account_holder;

    @JsonProperty(required = true)
    private String iban;
    private String bicswift;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;



    public String getAccount_holder() {
        return account_holder;
    }


    public void setAccount_holder(String account_holder) {
        this.account_holder = account_holder;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBicswift() {
        return bicswift;
    }

    public void setBicswift(String bicswift) {
        this.bicswift = bicswift;
    }



    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }


    public BankAccount.bankAccountTypeEnum getBankAccountType() {
        return bankAccountType;
    }


}
