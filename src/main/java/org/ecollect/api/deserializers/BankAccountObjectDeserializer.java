package org.ecollect.api.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.ecollect.api.classes.BankAccount;
import org.ecollect.api.classes.BankAccountAccNumber;
import org.ecollect.api.classes.BankAccountIBAN;

import java.io.IOException;
import java.util.ArrayList;

public class BankAccountObjectDeserializer extends StdDeserializer {

    public BankAccountObjectDeserializer() {
        this(null);
    }


    public BankAccountObjectDeserializer(Class<?> vc) {
        super(vc);
    }

    static final String IBANJSON = "iban"; // "iban" written in lowercase comes in the json

    @Override
    public BankAccount deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode bankAcc = p.getCodec().readTree(p);
        if (bankAcc == null) return null;


            JsonNode iban = bankAcc.get(BankAccountArrayDeserializer.IBANJSON);
            JsonParser bankAccParser = bankAcc.traverse();
            bankAccParser.setCodec(p.getCodec());

            BankAccount bankAccount = new BankAccount();

            if (iban != null) { // object is BankAccountIBAN
                try {
                    bankAccount.setBankAccountType(BankAccount.bankAccountTypeEnum.IBAN);
                    BankAccountIBAN accIBAN = bankAccParser.readValueAs(BankAccountIBAN.class);
                    bankAccount.setBankAccountIBAN(accIBAN);
                } catch (IOException e) {
                    throw e;
                }
            } else { // object is BankAccountAccNumber
                try {
                    bankAccount.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
                    BankAccountAccNumber accAccNumber = bankAccParser.readValueAs(BankAccountAccNumber.class);
                    bankAccount.setBankAccountAccNumber(accAccNumber);
                } catch (IOException e) {
                    throw e;
                }
            }

        return bankAccount;
    }


}


