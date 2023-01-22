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

public class PaymentClaimGetSingleTest {

    private EcollectAPIHandler api;

    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    @Test
    public void PaymentGetSingleTest() throws Exception {

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        Claim claim = ClaimTestHelper.ClaimPostTest(api, custRef);

        Payment payment = PaymentTestHelper.createPaymentWithClaim(custRef, claim);


        TimeUnit.SECONDS.sleep(10); // Due to many calculations sagas are pretty slow, so please wait for a while



        Payment paymentPosted = PaymentTestHelper.postPayment(api, payment);

        TimeUnit.SECONDS.sleep(5); // Due to many calculations sagas are pretty slow, so please wait for a while


        Payment paymentGotten = PaymentTestHelper.getPaymentSingle(api, paymentPosted);


        Assert.assertTrue(paymentGotten.getAmount().getValue().equals(payment.getAmount().getValue()));
    }
}

