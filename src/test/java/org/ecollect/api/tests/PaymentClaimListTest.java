package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.ClaimTestHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.ecollect.api.tests.helpers.PaymentTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class PaymentClaimListTest {

    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void PaymentListTest() throws Exception {

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        Claim claim = ClaimTestHelper.ClaimPostTest(api, custRef);

        Payment payment = PaymentTestHelper.createPaymentWithClaim(custRef, claim);

        Payment paymentPosted = PaymentTestHelper.postPayment(api, payment);
        Payment paymentPosted2 = PaymentTestHelper.postPayment(api, payment);



        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while



        PaymentList paymentList = PaymentTestHelper.getPaymentList(api);


        Assert.assertTrue(paymentList.getData().size()>0);
    }
}

