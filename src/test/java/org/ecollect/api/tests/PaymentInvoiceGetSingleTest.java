package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.tests.helpers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PaymentInvoiceGetSingleTest {

    private EcollectAPIHandler api;

    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void PaymentInvoiceGetSingleTest() throws Exception {

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        Invoice invoice = InvoiceTestHelper.createInvoice(custRef);
        Invoice invoicePosted = InvoiceTestHelper.postInvoice(api, invoice);

//        TimeUnit.SECONDS.sleep(3);

        Payment payment = PaymentTestHelper.createPaymentWithInvoice(custRef, invoicePosted);
        Payment paymentPosted = PaymentTestHelper.postPayment(api, payment);

        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while



        Payment paymentGotten = PaymentTestHelper.getPaymentSingle(api, paymentPosted);

        TimeUnit.SECONDS.sleep(5); // Due to many calculations sagas are pretty slow, so please wait for a while


        Assert.assertTrue(paymentGotten.getAmount().getValue().equals(payment.getAmount().getValue()));
    }
}

