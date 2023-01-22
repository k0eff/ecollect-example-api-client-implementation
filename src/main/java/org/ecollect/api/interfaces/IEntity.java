package org.ecollect.api.interfaces;

import org.ecollect.api.classes.*;

import java.util.ArrayList;

public interface IEntity {
    public ArrayList<Address> getAddresses();
    ArrayList<Contact> getContacts();
    ArrayList<BankAccount> getBank_accounts();
    String getEntityType();
    Person getPerson();
    Organisation getOrganisation();
}
