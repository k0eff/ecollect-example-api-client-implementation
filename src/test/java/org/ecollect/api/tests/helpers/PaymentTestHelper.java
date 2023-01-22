package org.ecollect.api.tests.helpers;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class PaymentTestHelper {


    public static Payment createPaymentWithClaim(Customer custRef, Claim claim) throws Exception {
        // credit metadata

        PaymentCreditMetadata creditMetaContract = new PaymentCreditMetadata();
        creditMetaContract.setType(PaymentCreditTypeEnum.CONTRACT_REF);
        creditMetaContract.setValue("Contract Metadata Test");

        PaymentCreditMetadata creditMetaInv = new PaymentCreditMetadata();
        creditMetaInv.setType(PaymentCreditTypeEnum.INVOICE_REF);
        creditMetaInv.setValue("Invoice Metadata Test");

        ArrayList<PaymentCreditMetadata> paymentMetadata = new ArrayList<>(1);
        paymentMetadata.add(creditMetaContract);
        paymentMetadata.add(creditMetaInv);


        // Amount
        Amount amount = new Amount();
        Currency curr = Currency.getInstance("EUR");
        amount.setCurrency(curr);
        amount.setValue(new BigDecimal(100.5));


        String custRefId = custRef.getId();


        // Payment
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setYour_reference("Your Reference Test");
        payment.setValue_date(LocalDateTime.of(2010,1,1,0,0,0));

        payment.setCustomer(custRefId);

        String claimId = claim.getId();
        payment.setClaim(claimId);

        return payment;


    }


        public static Payment createPaymentWithInvoice(Customer custRef, Invoice invoicePosted) throws Exception {
            return createPaymentWithInvoiceAmt(custRef, invoicePosted, 100.5);
        }


        public static Payment createPaymentWithInvoiceAmt(Customer custRef, Invoice invoicePosted, Double amt) throws Exception {
        // credit metadata

        PaymentCreditMetadata creditMetaContract = new PaymentCreditMetadata();
        creditMetaContract.setType(PaymentCreditTypeEnum.CONTRACT_REF);
        creditMetaContract.setValue("Contract Metadata Test");

        PaymentCreditMetadata creditMetaInv = new PaymentCreditMetadata();
        creditMetaInv.setType(PaymentCreditTypeEnum.INVOICE_REF);
        creditMetaInv.setValue("Invoice Metadata Test");

        ArrayList<PaymentCreditMetadata> paymentMetadata = new ArrayList<>(1);
        paymentMetadata.add(creditMetaContract);
        paymentMetadata.add(creditMetaInv);


        // Amount
        Amount amount = new Amount();
        Currency curr = Currency.getInstance("EUR");
        amount.setCurrency(curr);
        amount.setValue(new BigDecimal(amt));


        String custRefId = custRef.getId();


        // Payment
        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setYour_reference("Your Reference Test");
        payment.setValue_date(LocalDateTime.of(2010,1,1,0,0,0));

        payment.setCustomer(custRefId);

        String invoiceId = invoicePosted.getId();
        payment.setInvoice(invoiceId);

        return payment;


    }



    public static Payment postPayment(EcollectAPIHandler api, Payment payment) throws EcollectAPIException, IOException {
        Payment paymentPosted = api.payment.postPayment(payment);
        return paymentPosted;
    }

    public static Payment getPaymentSingle(EcollectAPIHandler api, Payment payment) throws EcollectAPIException, IOException {
        return api.payment.getPaymentByObj(payment);
    }

    public static PaymentList getPaymentList(EcollectAPIHandler api) throws EcollectAPIException, IOException {
        return api.payment.getManyPayments(2, 0);
    }

}
