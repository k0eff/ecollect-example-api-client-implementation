package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class ClaimPostTest {


    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void ClaimPostTest() throws Exception {

        // claim metadata

        TransactionMetadata claimMetaContract = new TransactionMetadata();
        claimMetaContract.setType(TransactionMetadata.TypeEnum.CONTRACT_REFERENCE);
        claimMetaContract.setValue("Contract Metadata Test");

        TransactionMetadata claimMetaInv = new TransactionMetadata();
        claimMetaInv.setType(TransactionMetadata.TypeEnum.INVOICE_REFERENCE);
        claimMetaInv.setValue("Invoice Metadata Test");

        ArrayList<TransactionMetadata> claimMetadata = new ArrayList<>(1);
        claimMetadata.add(claimMetaContract);
        claimMetadata.add(claimMetaInv);


        // claim events

        ReceivableEvent eventInvoice = new ReceivableEvent();
        eventInvoice.setDescription("Description test");
        eventInvoice.setLocation("Location test");
        eventInvoice.setTitle("Title test");
        eventInvoice.setYour_reference("Reference test");
        eventInvoice.setType(ReceivableEvent.TypeEnum.INVOICE);
        eventInvoice.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));

        ReceivableEvent eventContravention = new ReceivableEvent();
        eventContravention.setDescription("Description test");
        eventContravention.setLocation("Location test");
        eventContravention.setTitle("Title test");
        eventContravention.setYour_reference("Reference test");
        eventContravention.setType(ReceivableEvent.TypeEnum.CONTRAVENTION);
        eventContravention.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));

        ArrayList<ReceivableEvent> eventList = new ArrayList<>(1);
        eventList.add(eventInvoice);
        eventList.add(eventContravention);


        // Amount
        Amount amount = new Amount();
        Currency curr = Currency.getInstance("EUR");
        amount.setCurrency(curr);
        amount.setValue(new BigDecimal(100.5));



        ContractualItemEnum contractualItem = ContractualItemEnum.CONTRACT_FOR_WORK;


        // Claim

        Claim claim = new Claim();
        claim.setAmount(amount);

        claim.setYour_reference("Your Reference Test");


        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        String custRefId = custRef.getId();

        claim.setCustomer(custRefId);

        claim.setAmount(amount);
        claim.setOccurrence_date(LocalDateTime.of(2010,1,1,0,0,0));
        claim.setDue_date(LocalDateTime.of(2010,1,1,0,0,0));
        claim.setSubject_matter("Subject Matter test");
        claim.setContractual_item(contractualItem);
        claim.setQuality(QualityEnum.SPECIAL);
        claim.setMetadata(claimMetadata);
        claim.setEvents(eventList);
        // claim is ready for submission


        try {
            Claim claimPosted = api.claim.postClaim(claim);
            Assert.assertTrue(claimPosted.getQualityJson().equals(claim.getQualityJson()));

        } catch (EcollectAPIException e) {
            System.out.println(e.getJsonRaw());
        }
    }
}
