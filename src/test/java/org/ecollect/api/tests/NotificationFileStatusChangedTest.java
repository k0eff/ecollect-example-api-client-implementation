package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.apimodels.NotificationApiModelFilters;
import org.ecollect.api.classes.*;
import org.ecollect.api.classes.notifications.NotFileInvoiceAttached;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.ecollect.api.tests.helpers.InvoiceTestHelper;
import org.ecollect.api.tests.helpers.PaymentTestHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class NotificationFileStatusChangedTest {


    /**
     * Test scenario:
     * 1. Create customer
     * 2. Submit invoice
     * 3. Submit direct payment
     * 4. Wait for file.invoiceAttached and check file id
     * 5. Check if a notification file.statusChanged exists for that invoice
     */




    private EcollectAPIHandler api;
    private Invoice invoicePosted;
    private Customer customerPosted;
    private Payment paymentPosted;

    public NotificationFileStatusChangedTest () throws Exception {
        api = ApiHandlerHelper.setupApi();
        customerPosted  = CustomerTestHelper.createCustomerOrg(api);

        TimeUnit.SECONDS.sleep(15);
        Invoice invoice = InvoiceTestHelper.createInvoice(customerPosted);
        invoicePosted = InvoiceTestHelper.postInvoice(api, invoice);

        TimeUnit.SECONDS.sleep(15);
        Payment payment = PaymentTestHelper.createPaymentWithInvoiceAmt(customerPosted, invoicePosted, 6660.0); // 6660 is the total amount of the invoice items
        paymentPosted = PaymentTestHelper.postPayment(api, payment);

        TimeUnit.SECONDS.sleep(15);

    }



    @Test
    public void realTest() throws EcollectAPIException, IOException {
        NotificationApiModelFilters filter = new NotificationApiModelFilters();
        NotificationV2List nots = api.notification.getManyNotifications(20,0, filter);

        String fileId = "";
        String invoiceId;
        boolean notStatusChangedExists=false;


        //get File id
        for (NotificationV2 not : nots.getData()) {
            if (!not.getType().equals("file.invoiceAttached")) continue;

            NotFileInvoiceAttached notFIA = (NotFileInvoiceAttached) not.getPayload();
            invoiceId = notFIA.getId();
            if (!invoiceId.equals(invoicePosted.getId())) continue;

            fileId = not.getAggId();

        }

        // check if there is statusChanged notification for the gotten file
        for (NotificationV2 not : nots.getData()) {
            if (!not.getType().equals("file.statusChanged")) continue;
            if (not.getAggId().equals(fileId)) notStatusChangedExists = true;
        }

        Assert.assertTrue(notStatusChangedExists);
    }



}
