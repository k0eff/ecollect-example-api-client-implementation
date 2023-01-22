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

public class BankAccountArrayDeserializer extends StdDeserializer {

    public BankAccountArrayDeserializer() {
        this(null);
    }


    public BankAccountArrayDeserializer(Class<?> vc) {
        super(vc);
    }

    static final String IBANJSON = "iban"; // "iban" written in lowercase comes in the json



    @Override
    public ArrayList<BankAccount> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if (node == null) return null;

        ArrayList<BankAccount> list = new ArrayList<>(1);

        for (JsonNode bankAcc : node) {
            //each BankAccount object

            JsonNode iban = bankAcc.get(BankAccountArrayDeserializer.IBANJSON);
            JsonParser bankAccParser = bankAcc.traverse();
            bankAccParser.setCodec(p.getCodec());

            if (iban != null) { // object is BankAccountIBAN
                try {
                    BankAccount bankAccount = new BankAccount();
                    bankAccount.setBankAccountType(BankAccount.bankAccountTypeEnum.IBAN);
                    BankAccountIBAN accIBAN = bankAccParser.readValueAs(BankAccountIBAN.class);
                    bankAccount.setBankAccountIBAN(accIBAN);
                    list.add(bankAccount);
                } catch (IOException e) {
                    throw e;
                }
            } else { // object is BankAccountAccNumber
                try {
                    BankAccount bankAccount = new BankAccount();
                    bankAccount.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
                    BankAccountAccNumber accAccNumber = bankAccParser.readValueAs(BankAccountAccNumber.class);
                    bankAccount.setBankAccountAccNumber(accAccNumber);
                    list.add(bankAccount);
                } catch (IOException e) {
                    throw e;
                }
            }
        }

        if (!list.isEmpty()) return list;
        else return null;



        //TODO: write Exception
    }


}


