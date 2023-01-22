package org.ecollect.api.tests;

import com.neovisionaries.i18n.CountryCode;
import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.interfaces.IEntity;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerListTest {

    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    public void CustomerOrgPost() throws Exception {

        try {
            String your_reference = "Ref1234";


            // Customer Metadata

            CustomerMetadata metaUName = new CustomerMetadata();
            metaUName.setType(CustomerMetadata.TypeEnum.NAME);
            metaUName.setValue("eCollectTestUsername");

            CustomerMetadata metaLang = new CustomerMetadata();
            metaLang.setType(CustomerMetadata.TypeEnum.LANGUAGE);
            metaLang.setValue("English");

            ArrayList<CustomerMetadata> cmL = new ArrayList<>(2);
            cmL.add(metaUName);
            cmL.add(metaLang);


            // Customer Events

            CustomerEvent eventRegistration = new CustomerEvent();
            eventRegistration.setType(CustomerEvent.TypeEnum.REGISTRATION);
            eventRegistration.setTitle("eCollectTestEvent Title");
            eventRegistration.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
            eventRegistration.setYour_reference("eCollectTestReferenceReg");

            CustomerEvent eventLogin = new CustomerEvent();
            eventLogin.setType(CustomerEvent.TypeEnum.LOGIN);
            eventLogin.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
            eventRegistration.setYour_reference("eCollectTestReferenceLogin");

            ArrayList<CustomerEvent> cmE = new ArrayList<>(2);
            cmE.add(eventLogin);
            cmE.add(eventRegistration);


            // Entity Organisation


            Organisation org = new Organisation();
            org.setCountry_of_residence(CountryCode.DE);
            org.setType("GMBH");
            org.setName("eCollectOrgTestName");


            // Addresses
            Address addr = new Address();
            ArrayList<String> addrLnLst = new ArrayList<>(1);
            addrLnLst.add("eCollectTestAddrLn1");
            addrLnLst.add("eCollectTestAddrLn2");
            addr.setAddress_lines(addrLnLst); // required field
            addr.setZip("40212");
            addr.setCity("Dusseldorf"); // required field
            addr.setDistrict("Oberbilk");
            addr.setCountry(CountryCode.DE); // required field
            addr.setType(Address.TypeEnum.RESIDENCE);

            ArrayList<Address> addrList = new ArrayList<>(1);
            addrList.add(addr);


            // Contacts

            Contact contEmail = new Contact();
            contEmail.setType(Contact.TypeEnum.EMAIL);
            contEmail.setValue("eCollectTest@example.com");

            Contact contCell = new Contact();
            contCell.setType(Contact.TypeEnum.CELLPHONE);
            contCell.setValue("+4949494949494949");

            ArrayList<Contact> contList = new ArrayList<>(1);
            contList.add(contCell);
            contList.add(contEmail);


            // Bank Accounts
            /**
             * What we need to do is
             * 1. Create a BankAccountIBAN / BankAccountAccNumber object
             * 2. Create a BankAccount object, set its type and add the IBAN/AccNumber object to it.
             * 3. Create a ArrayList with BankAccount obj and set it to the entity
             */

            //Account type Account number
            BankAccountAccNumber bankAccountTypeAccNumber = new BankAccountAccNumber();
            bankAccountTypeAccNumber.setClearing_number("eCollect Test clearing number");
            bankAccountTypeAccNumber.setBank_country(CountryCode.BG);
            bankAccountTypeAccNumber.setAccount_number("eCollect Test account number");
            bankAccountTypeAccNumber.setAccount_holder("eCollect Test account holder");

            //Account type IBAN
            BankAccountIBAN bankAccountTypeIban = new BankAccountIBAN();
            bankAccountTypeIban.setIban("NL80RABO9639906824");
            bankAccountTypeIban.setBicswift("RABONL2U");
            bankAccountTypeIban.setAccount_holder("eCollect test account holder");


            // the array list with bank accounts down here should contain instances of the BankAccount class. Due to the polymorphism of the api objects,
            // you have to instantiate them and add the BankAccountType object.
            ArrayList<BankAccount> bankAccounts = new ArrayList<>(1);

            // set Bank Account for type BankAccountType Iban
            BankAccount bnkAccIban = new BankAccount();
            bnkAccIban.setBankAccountType(BankAccount.bankAccountTypeEnum.IBAN);
            bnkAccIban.setBankAccountIBAN(bankAccountTypeIban);

            //create Bank Account obj for type BankAccountType AccNumber
            BankAccount bnkAccAccNumber = new BankAccount();
            bnkAccAccNumber.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
            bnkAccAccNumber.setBankAccountAccNumber(bankAccountTypeAccNumber);

            // OK, now let's add the BankAccount objects to the Array list
            bankAccounts.add(bnkAccIban);
            bankAccounts.add(bnkAccAccNumber);

            // now we're finished and we may set this bank account list to the Organisation object (listed below)


            // Relations
            // Relations are currently not supported


            // Entity
            /**
             * To properly create an entity we need to follow these steps:
             * 1. Create a Person / Organisation
             * 2. Create an EntityPerson/organisation
             * 3. Create an Entity
             * 4. Set entity Type
             * 5. Set Entity Person / Entity Organisation to Entity
             * 6. Set Entity to Customer
             *
             * ###############
             */



            // Entity Organisation
            EntityOrganisation entOrg = new EntityOrganisation();
            entOrg.setOrganisation(org);
            entOrg.setAddresses(addrList);
            entOrg.setContacts(contList);
            entOrg.setBank_accounts(bankAccounts);



            Customer customer = new Customer();
            customer.setEntity((IEntity) entOrg);
            customer.setEvents(cmE);
            customer.setMetadata(cmL);



            // Now we're ready to make a new API request

            Customer custPosted = api.customer.postCustomer(customer);


        } catch (EcollectAPIException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }
    public void CustomerPersonPost () throws Exception {
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
            BankAccountIBAN bankAccountIBAN = new BankAccountIBAN();
            bankAccountIBAN.setAccount_holder("Account holder name");
            bankAccountIBAN.setBicswift("INGBNL2A");
            bankAccountIBAN.setIban("NL55INGB3659950165");

            BankAccount bankAccount1 = new BankAccount();
            bankAccount1.setBankAccountType(BankAccount.bankAccountTypeEnum.IBAN);
            bankAccount1.setBankAccountIBAN(bankAccountIBAN);

            bankAccounts.add(bankAccount1);



            BankAccountAccNumber bankAccountAccNumber = new BankAccountAccNumber();
            bankAccountAccNumber.setAccount_holder("Account holder 2");
            bankAccountAccNumber.setAccount_number("123123123");
            bankAccountAccNumber.setBank_country(CountryCode.BG);
            bankAccountAccNumber.setClearing_number("141231");

            BankAccount bankAccount2 = new BankAccount();
            bankAccount2.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
            bankAccount2.setBankAccountAccNumber(bankAccountAccNumber);
            bankAccounts.add(bankAccount2);


            EntityPerson entityPerson = new EntityPerson(person);

            entityPerson.setAddresses(addList);
            entityPerson.setContacts(contactList);
            entityPerson.setBank_accounts(bankAccounts);



            CustomerMetadata customerMetadata = new CustomerMetadata();
            customerMetadata.setType(CustomerMetadata.TypeEnum.LANGUAGE);
            customerMetadata.setValue("Bulgarian");




            CustomerMetadata customerMetadata2 = new CustomerMetadata();
            customerMetadata2.setType(CustomerMetadata.TypeEnum.IP);
            customerMetadata2.setValue("127.0.0.1");

            ArrayList<CustomerMetadata> customerMetadataAll = new ArrayList<CustomerMetadata>(1);
            customerMetadataAll.add(customerMetadata);
            customerMetadataAll.add(customerMetadata2);

            CustomerEvent customerEvent = new CustomerEvent();
            customerEvent.setType(CustomerEvent.TypeEnum.REGISTRATION);
            customerEvent.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
            customerEvent.setLocation("Location 1");
            customerEvent.setYour_reference("your_reference_1");
            customerEvent.setTitle("Title 1");
            customerEvent.setDescription("Description 1");



            ArrayList<CustomerEvent> customerEventsList = new ArrayList<CustomerEvent>(1);
            customerEventsList.add(customerEvent);

            Customer customer = new Customer();
            customer.setEntity(entityPerson);
            customer.setMetadata(customerMetadataAll);
            customer.setEvents(customerEventsList);


            Customer customerPosted = api.customer.postCustomer(customer);

        } catch (EcollectAPIException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }




    @Before
    public void create2Customers() throws Exception {
            this.CustomerOrgPost();
            this.CustomerPersonPost();
    }





    @Test
    public void checkCustomerList() throws EcollectAPIException, IOException {

        /**
         * Get customer list using api
         */
        CustomerList cl = api.customer.getManyCustomers(2, 0);
        Assert.assertTrue(cl.getData().size() == 2);
    }
}
