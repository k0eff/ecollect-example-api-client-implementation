package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.apimodels.NotificationApiModelFilters;
import org.ecollect.api.classes.*;
import org.ecollect.api.classes.notifications.NotFileInvoiceAttached;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.ecollect.api.tests.helpers.InvoiceTestHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NotificationsDeliveredTest {

    EcollectAPIHandler api;
    Customer customerPosted;
    Invoice invoicePosted;

    public NotificationsDeliveredTest() throws Exception {
        api = ApiHandlerHelper.setupApi();
        customerPosted = CustomerTestHelper.createCustomerOrg(api);
        invoicePosted = InvoiceTestHelper.postInvoice(api, InvoiceTestHelper.createInvoice(customerPosted));

        TimeUnit.SECONDS.sleep(15);
    }

    @Test
    public void NotificationsDeliveredTest1() throws EcollectAPIException, IOException {
        NotificationApiModelFilters flt = new NotificationApiModelFilters();
        NotificationV2List nL = api.notification.getManyNotifications(100, 0, flt);
        NotFileInvoiceAttached nIA;

        Boolean notContained = false;
        for (NotificationV2 n : nL.getData()) {
            if (n.getType().equals("file.invoiceAttached")) {
                nIA = (NotFileInvoiceAttached) n.getPayload();
                if (nIA.getId().equals(invoicePosted.getId())) {
                    ArrayList<String> nots = new ArrayList<>();
                    nots.add(n.getId());

                    NotificationsDelivered notDel = new NotificationsDelivered();
                    notDel.setDelivered(nots);

                    NotificationsDelivered notDelPosted = api.notification.markNotificationsDelivered(notDel);
                    if (notDel.getDelivered().get(0).equals(n.getId())) notContained = true;
                }
            }
        }

        Assert.assertTrue(notContained);
    }

}
