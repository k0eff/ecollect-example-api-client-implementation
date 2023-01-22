package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.apimodels.NotificationApiModelFilters;
import org.ecollect.api.classes.NotificationV2;
import org.ecollect.api.classes.NotificationV2List;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class NotificationFiltersTest {

    /**
     * This test is hardcoded. Please create undelivered notifications manually and then test them here
     */



    private EcollectAPIHandler api;
    private final String[] notIds = {"not-5358X4V965P3QHM4", "not-5358X4V965PW51R6"};


    public NotificationFiltersTest() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void NotificationFiltersTestUndelivered() throws EcollectAPIException, IOException {
        NotificationApiModelFilters filters = new NotificationApiModelFilters();
        filters.setUndelivered(true);
        NotificationV2List notificationV2List = api.notification.getManyNotifications(2,0, filters);

        Boolean notFound = false;
        for (NotificationV2 n : notificationV2List.getData() ) {

            for (String s : notIds) {
                if (s.equals(n.getId())) notFound = notFound | true;
            }
        }
        Assert.assertTrue(notFound.equals(true));
    }


    @Test
    public void NotificationFiltersTestRegardingCustomer() throws EcollectAPIException, IOException {
        NotificationApiModelFilters filters = new NotificationApiModelFilters();
        filters.setRegardingCustomer("cus-5358X4V965HWQNK9");
        NotificationV2List notificationV2List = api.notification.getManyNotifications(2,0, filters);

        Assert.assertTrue(notificationV2List.getData().size() > 0);
    }


    @Test
    public void NotificationFiltersTestRegardingFile() throws EcollectAPIException, IOException {
        NotificationApiModelFilters filters = new NotificationApiModelFilters();
        filters.setRegardingFile("fil-5358X4V965PC5F3");
        NotificationV2List notificationV2List = api.notification.getManyNotifications(2,0, filters);

        Assert.assertTrue(notificationV2List.getData().size() > 0);
    }


}
