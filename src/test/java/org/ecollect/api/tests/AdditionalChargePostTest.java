package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class AdditionalChargePostTest {

    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void ChargePostTest() throws Exception {

        // charge metadata

        TransactionMetadata chargeMetaContract = new TransactionMetadata();
        chargeMetaContract.setType(TransactionMetadata.TypeEnum.CONTRACT_REFERENCE);
        chargeMetaContract.setValue("Contract Metadata Test");

        TransactionMetadata chargeMetaInv = new TransactionMetadata();
        chargeMetaInv.setType(TransactionMetadata.TypeEnum.INVOICE_REFERENCE);
        chargeMetaInv.setValue("Invoice Metadata Test");

        ArrayList<TransactionMetadata> chargeMetadata = new ArrayList<>(1);
        chargeMetadata.add(chargeMetaContract);
        chargeMetadata.add(chargeMetaInv);


        // charge events

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


        // charge

        AdditionalCharge charge = new AdditionalCharge();
        charge.setAmount(amount);

        charge.setYour_reference("Your Reference Test");

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        String custRefId = custRef.getId();

        charge.setCustomer(custRefId);
        charge.setAmount(amount);
        charge.setOccurrence_date(LocalDateTime.of(2010,1,1,0,0,0));
        charge.setMetadata(chargeMetadata);
        charge.setEvents(eventList);
        charge.setAdditional_charge_type(AdditionalChargeTypeEnum.ADVISORY_FEE);
        charge.setBeneficiary(BeneficiaryEnum.ACCOUNT);
        // charge is ready for submission


        try {
            AdditionalCharge chargePosted = api.charge.postCharge(charge);

        } catch (EcollectAPIException e) {
            System.out.println(e.getJsonRaw());
        }
    }
}
