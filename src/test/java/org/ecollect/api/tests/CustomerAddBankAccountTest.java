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

public class CustomerAddBankAccountTest {


    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    public Customer createCustomerOrg() throws Exception {

        try {


            String your_reference = "Ref1234";




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



            Customer customer = new Customer();
            customer.setEntity((IEntity) entOrg);


            // Now we're ready to make a new API request

            Customer custPosted = api.customer.postCustomer(customer);

            return custPosted;
        } catch (EcollectAPIException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public BankAccount createBankAccount() {

        //Account type Account number
        BankAccountAccNumber bankAccountTypeAccNumber = new BankAccountAccNumber();
        bankAccountTypeAccNumber.setClearing_number("eCollect Test clearing number");
        bankAccountTypeAccNumber.setBank_country(CountryCode.BG);
        bankAccountTypeAccNumber.setAccount_number("eCollect Test account number");
        bankAccountTypeAccNumber.setAccount_holder("eCollect Test account holder");


        //create Bank Account obj for type BankAccountType AccNumber
        BankAccount bnkAccAccNumber = new BankAccount();
        bnkAccAccNumber.setBankAccountType(BankAccount.bankAccountTypeEnum.AccNumber);
        bnkAccAccNumber.setBankAccountAccNumber(bankAccountTypeAccNumber);

        // now we're finished and we may set this bank account list to the Organisation object (listed below)
        return bnkAccAccNumber;
    }


    @Test
    public void CustomerOrgAddBankAccountTest() throws Exception {
        Customer customerOrg = createCustomerOrg();

        BankAccount recordBefore = createBankAccount();
        Customer custAddedRecord = api.customer.addBankAccount(customerOrg, recordBefore);

        ArrayList<BankAccount> list = custAddedRecord.getEntity().getBank_accounts();
        BankAccount recordContained = list.get(0);
        Assert.assertTrue(recordBefore.getBankAccountAccNumber().getAccount_holder().equals(recordContained.getBankAccountAccNumber().getAccount_holder()));
    }


}
