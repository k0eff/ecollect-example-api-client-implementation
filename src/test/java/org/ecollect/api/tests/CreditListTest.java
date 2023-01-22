package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CreditTestHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.ecollect.api.tests.helpers.InvoiceTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

public class CreditListTest {

    private EcollectAPIHandler api;

    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Before
    public void makeCredits() throws Exception {

            Customer customer = CustomerTestHelper.createCustomerOrg(api);
            Invoice invoice = InvoiceTestHelper.createInvoice(customer);
            Invoice invoicePosted = InvoiceTestHelper.postInvoice(api, invoice);
            Credit credit = CreditTestHelper.createCredit(customer, invoicePosted);
            Credit creditPosted = CreditTestHelper.postCredit(api, credit);

    }


    @Test
    public void checkCreditList() throws EcollectAPIException, IOException, InterruptedException {

        /**
         * Get customer list using api
         */
        TimeUnit.SECONDS.sleep(3); // Due to many calculations sagas are pretty slow, so please wait for a while

        CreditList cl = CreditTestHelper.getCreditList(api);
        Assert.assertTrue(cl.getData().size() > 0);
    }
}
