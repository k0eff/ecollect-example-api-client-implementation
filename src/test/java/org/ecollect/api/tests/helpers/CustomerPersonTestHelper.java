package org.ecollect.api.tests.helpers;

import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerPersonTestHelper {

public static BankAccountIBAN createBankAccountIban() {
    BankAccountIBAN bankAccountIBAN = new BankAccountIBAN();
    bankAccountIBAN.setAccount_holder("Account holder name");
    bankAccountIBAN.setBicswift("INGBNL2A");
    bankAccountIBAN.setIban("NL55INGB3659950165");
    return bankAccountIBAN;
}


public static BankAccountAccNumber createBankAccountAccNumber() {
    BankAccountAccNumber bankAccountAccNumber = new BankAccountAccNumber();
    bankAccountAccNumber.setAccount_holder("Account holder 2");
    bankAccountAccNumber.setAccount_number("123123123");
    bankAccountAccNumber.setBank_country(CountryCode.BG);
    bankAccountAccNumber.setClearing_number("141231");
    return bankAccountAccNumber;
}

public static CustomerMetadata createCustomerMetadata() {
    CustomerMetadata customerMetadata = new CustomerMetadata();
    customerMetadata.setType(CustomerMetadata.TypeEnum.LANGUAGE);
    customerMetadata.setValue("Bulgarian");
    return customerMetadata;
}


public static CustomerEvent createCustomerEvent() {
    CustomerEvent customerEvent = new CustomerEvent();
    customerEvent.setType(CustomerEvent.TypeEnum.REGISTRATION);
    customerEvent.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
    customerEvent.setLocation("Location 1");
    customerEvent.setYour_reference("your_reference_1");
    customerEvent.setTitle("Title 1");
    customerEvent.setDescription("Description 1");
    return customerEvent;
}



    public static Customer CustomerObjCreate(
            BankAccountIBAN bankAccountIBAN,
            BankAccountAccNumber bankAccountAccNumber,
            CustomerMetadata customerMetadata,
            CustomerEvent customerEvent) throws Exception {
        try {
            Person person = new Person();
            person.setGiven_names("Test");
            person.setSurname("le Test");
            person.setSex(Person.SexEnum.M);
            person.setTitle("Mr.");
            person.setDate_of_birth(LocalDateTime.of(2010, 1, 1, 0, 0, 0));


            Place place = new Place();
            place.setCity("eCollect Place Test");
            place.setCountry(CountryCode.BG);

            person.setPlace_of_birth(place);
            person.setDate_of_birth(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
            person.setPlace_of_death(place);
            person.setDate_of_death(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
            person.setNationality(CountryCode.BG);
            person.setCountry_of_residence(CountryCode.BG);



            Address address1 = new Address();
            ArrayList<String> address_lines1 = new ArrayList<String>(3);
            address_lines1.add("addr ln 1");
            address_lines1.add("addr ln 2");
            address1.setAddress_lines(address_lines1);
            address1.setDistrict("Center");
            address1.setCity("Sofia");
            address1.setState("Sofia");
            address1.setCountry(CountryCode.BG);
            address1.setType(Address.TypeEnum.BILLING);
            address1.setZip("1000");



            CountryCode countryCode_bg = CountryCode.BG;
            address1.setCountry(countryCode_bg);

            ArrayList<Address> addList = new ArrayList<Address>(1);
            addList.add(address1);

            ArrayList<Contact> contactList = new ArrayList<Contact>(2);

            Contact contact1 = new Contact();
            contact1.setType(Contact.TypeEnum.CELLPHONE);
            contact1.setValue("0888888");
            Contact contact2 = new Contact();
            contact2.setType(Contact.TypeEnum.EMAIL);
            contact2.setValue("blabla@bla.bla");

            contactList.add(contact1);
            contactList.add(contact2);


            ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>(2);

            BankAccount bankAccount1 = new BankAccount();
            bankAccount1.setBankAccountType(BankAccount.bankAccountTypeEnum.IBAN);
            bankAccount1.setBankAccountIBAN(bankAccountIBAN);

            bankAccounts.add(bankAccount1);


            BankAccount bankAccount2 = new BankAccount();
            bankAccount2.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
            bankAccount2.setBankAccountAccNumber(bankAccountAccNumber);
            bankAccounts.add(bankAccount2);


            EntityPerson entityPerson = new EntityPerson(person);

            entityPerson.setAddresses(addList);
            entityPerson.setContacts(contactList);
            entityPerson.setBank_accounts(bankAccounts);



            CustomerMetadata customerMetadata2 = new CustomerMetadata();
            customerMetadata2.setType(CustomerMetadata.TypeEnum.IP);
            customerMetadata2.setValue("127.0.0.1");

            ArrayList<CustomerMetadata> customerMetadataAll = new ArrayList<CustomerMetadata>(1);
            customerMetadataAll.add(customerMetadata);
            customerMetadataAll.add(customerMetadata2);


            ArrayList<CustomerEvent> customerEventsList = new ArrayList<CustomerEvent>(1);
            customerEventsList.add(customerEvent);

            Customer customer = new Customer();
            customer.setEntity(entityPerson);
            customer.setMetadata(customerMetadataAll);
            customer.setEvents(customerEventsList);

            return customer;

        } catch (EcollectAPIException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }


    public static Customer postCustomer(Customer customer, EcollectAPIHandler api) throws EcollectAPIException, IOException {
        return api.customer.postCustomer(customer);
    }



}
