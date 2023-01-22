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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

public class CreditPostTest {

    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }

    @Test
    public void CreditPostTest() throws Exception {

        Customer customer = CustomerTestHelper.createCustomerOrg(api);
        Invoice invoice = InvoiceTestHelper.createInvoice(customer);
        Invoice invoicePosted = InvoiceTestHelper.postInvoice(api, invoice);

        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while
        // invoice has to be attached to file

        Credit credit = CreditTestHelper.createCredit(customer, invoicePosted);
        Credit creditPosted = CreditTestHelper.postCredit(api, credit);


        // notice creditPosted doesn't have invoice set.
        // that's because the invoice has to be matched to its file
        // that requires some time, as it is run via a saga
        // please allow some time for that to happen

        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while

        Credit creditGotten = CreditTestHelper.getCreditSingle(api, creditPosted); // because saga needs to run in order credit to be mathched to file/invoice

        Assert.assertTrue(creditGotten.getMatched_entries().get(0).getEntries().contains(invoicePosted.getId()));
        // notice the aforementioned invoice is in matched_entries -> entries
        // but it may or may not be there depending on what has been matched
    }
}
