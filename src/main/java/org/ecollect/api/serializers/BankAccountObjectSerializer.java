package org.ecollect.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.ecollect.api.classes.BankAccount;
import org.ecollect.api.interfaces.IBankAccountType;

import java.io.IOException;
import java.util.ArrayList;

public class BankAccountObjectSerializer extends StdSerializer {

    public BankAccountObjectSerializer() {
        this(null);
    }

    public BankAccountObjectSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        BankAccount bankAccount = (BankAccount) value;

        try {
            if (bankAccount.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.IBAN)) {
                gen.writeObject(bankAccount.getBankAccountIBAN());
            } else if (bankAccount.getBankAccountType().equals(BankAccount.bankAccountTypeEnum.AccNumber)) {
                gen.writeObject(bankAccount.getBankAccountAccNumber());
            } else {
                throw new IOException("Error serializing Bank Account: unknown BankAccount type");
            }
        } catch (IOException e) {
            throw e;
        }
    }
}


