package org.ecollect.api.tests.helpers;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.tests.config.TestConfig;
import org.junit.Before;

public class ApiHandlerHelper {

    private static EcollectAPIHandler api;


    public static EcollectAPIHandler setupApi() {
        if (ApiHandlerHelper.api == null) ApiHandlerHelper.api = new EcollectAPIHandler(TestConfig.authToken, TestConfig.urlString, TestConfig.apiReqTimeout);
        return ApiHandlerHelper.api;
    }
}
