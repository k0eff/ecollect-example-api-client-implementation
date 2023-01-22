package org.ecollect.api.tests.helpers;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class CreditTestHelper {


    public static Credit createCredit(Customer custRef, Invoice invoice) throws Exception {

        // credit metadata

        PaymentCreditMetadata creditMetaContract = new PaymentCreditMetadata();
        creditMetaContract.setType(PaymentCreditTypeEnum.CONTRACT_REF);
        creditMetaContract.setValue("Contract Metadata Test");

        PaymentCreditMetadata creditMetaInv = new PaymentCreditMetadata();
        creditMetaInv.setType(PaymentCreditTypeEnum.INVOICE_REF);
        creditMetaInv.setValue("Invoice Metadata Test");

        ArrayList<PaymentCreditMetadata> creditMetadata = new ArrayList<>(1);
        creditMetadata.add(creditMetaContract);
        creditMetadata.add(creditMetaInv);


// credit events
// currently not supported: 2020-06-03
//        ReceivableEvent eventInvoice = new ReceivableEvent();
//        eventInvoice.setDescription("Description test");
//        eventInvoice.setLocation("Location test");
//        eventInvoice.setTitle("Title test");
//        eventInvoice.setYour_reference("Reference test");
//        eventInvoice.setType(ReceivableEvent.TypeEnum.INVOICE);
//        eventInvoice.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
//
//        ReceivableEvent eventContravention = new ReceivableEvent();
//        eventContravention.setDescription("Description test");
//        eventContravention.setLocation("Location test");
//        eventContravention.setTitle("Title test");
//        eventContravention.setYour_reference("Reference test");
//        eventContravention.setType(ReceivableEvent.TypeEnum.CONTRAVENTION);
//        eventContravention.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
//
//        ArrayList<ReceivableEvent> eventList = new ArrayList<>(1);
//        eventList.add(eventInvoice);
//        eventList.add(eventContravention);


        // Amount
        Amount amount = new Amount();
        Currency curr = Currency.getInstance("EUR");
        amount.setCurrency(curr);
        amount.setValue(new BigDecimal(100.5));


        ContractualItemEnum contractualItem = ContractualItemEnum.CONTRACT_FOR_WORK;


        // Credit

        Credit credit = new Credit();
        credit.setAmount(amount);
        credit.setYour_reference("Your Reference Test");


        String custRefId = custRef.getId();
        String invoiceId = invoice.getId();


        credit.setAmount(amount);
        credit.setValue_date(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
        credit.setMetadata(creditMetadata);
        credit.setCustomer(custRefId);
        credit.setInvoice(invoiceId);
        // TODO: implement credit events (currently not supported)

        // credit is ready for submission

        return credit;

    }


    public static Credit postCredit(EcollectAPIHandler api, Credit credit) throws Exception {
        try {
            Credit creditPosted = api.credit.postCredit(credit);
            return creditPosted;
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public static Credit getCreditSingle(EcollectAPIHandler api, Credit credit) throws EcollectAPIException, IOException {
        return api.credit.getCreditByObj(credit);
    }


    public static CreditList getCreditList(EcollectAPIHandler api) throws EcollectAPIException, IOException {
        return api.credit.getManyCredits(2, 0);
    }

}
