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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CustomerAddMetadataTest {


    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    public Customer createCustomerOrg() throws Exception {

        try {


            String your_reference = "Ref1234";



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
            entOrg.setBank_accounts(bankAccounts);



            Customer customer = new Customer();
            customer.setEntity((IEntity) entOrg);
            customer.setEvents(cmE);


            // Now we're ready to make a new API request

            Customer custPosted = api.customer.postCustomer(customer);

            return custPosted;
        } catch (EcollectAPIException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }

    public CustomerMetadata createMetadata() {

        CustomerMetadata metaLang = new CustomerMetadata();
        metaLang.setType(CustomerMetadata.TypeEnum.LANGUAGE);
        metaLang.setValue("English");
        return metaLang;
    }


    @Test
    public void CustomerOrgAddMetadataTest() throws Exception {
        Customer customerOrg = createCustomerOrg();

        CustomerMetadata recordBefore = createMetadata();
        Customer custAddedRecord = api.customer.addMetadata(customerOrg, recordBefore);

        ArrayList<CustomerMetadata> list = custAddedRecord.getMetadata();

        CustomerMetadata recordContained = list.get(0);
        Assert.assertTrue(recordBefore.getValue().equals(recordContained.getValue()));
    }


}
