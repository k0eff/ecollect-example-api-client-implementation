package org.ecollect.api.abstractClasses;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.classes.Address;
import org.ecollect.api.classes.BankAccount;
import org.ecollect.api.classes.Contact;
import org.ecollect.api.classes.Document;
import org.ecollect.api.deserializers.BankAccountArrayDeserializer;
import org.ecollect.api.serializers.BankAccountArraySerializer;

import java.util.ArrayList;

public abstract class AEntityCommon {
    private ArrayList<Address> addresses;
    private ArrayList<Contact> contacts;

    @JsonDeserialize(using = BankAccountArrayDeserializer.class)
    @JsonSerialize(using = BankAccountArraySerializer.class)
    private ArrayList<BankAccount> bank_accounts;


    private ArrayList<Document> documents;


    public ArrayList<Address> getAddresses() {
        return addresses;
    }
    public AEntityCommon setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
        return this;
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
    public AEntityCommon setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }


    public ArrayList<BankAccount> getBank_accounts() {
        return bank_accounts;
    }
    public AEntityCommon setBank_accounts(ArrayList<BankAccount> bank_accounts) {
        this.bank_accounts = bank_accounts;
        return this;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
