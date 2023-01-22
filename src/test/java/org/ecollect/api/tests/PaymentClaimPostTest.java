package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.ClaimTestHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.ecollect.api.tests.helpers.PaymentTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

public class PaymentClaimPostTest {

    private EcollectAPIHandler api;

    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void PaymentPostTest() throws Exception {

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        Claim claim = ClaimTestHelper.ClaimPostTest(api, custRef);

        Payment payment = PaymentTestHelper.createPaymentWithClaim(custRef, claim);


        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while

        Payment paymentPosted = PaymentTestHelper.postPayment(api, payment);

        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while

        Payment paymentGotten = PaymentTestHelper.getPaymentSingle(api, paymentPosted);

        Assert.assertTrue(paymentPosted.getAmount().getValue().equals(payment.getAmount().getValue()));
        Assert.assertTrue(paymentPosted.getAmount().getValue().equals(paymentGotten.getAmount().getValue()));
        Assert.assertTrue(paymentGotten.getMatched_entries().size()>0);

    }
}

