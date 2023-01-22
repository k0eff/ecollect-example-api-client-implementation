package org.ecollect.api.tests;


import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class OrderPostTest {


    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }


    public OrderEvent createOrderEvent() {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setType(OrderEvent.TypeEnum.BOOKING);
        orderEvent.setDescription("OrderEvent Description");
        orderEvent.setTitle("Order event title");
        orderEvent.setOccurrence(LocalDateTime.of(2010,1,1,0,0,0));
        orderEvent.setLocation("Order Event Location");
        orderEvent.setYour_reference("Order Event Reference");
        return orderEvent;
    }

    public OrderMetadata createOrderMetadata() {
        OrderMetadata orderMetadata = new OrderMetadata();
        orderMetadata.setType(OrderMetadata.PredefinedTypeEnum.PAYMENT_TYPE);
        orderMetadata.setValue("Order Metadata value");
        return orderMetadata;
    }


    public Claim createClaim() throws Exception {
        Claim claim = new Claim();
        claim.setBeneficiary(BeneficiaryEnum.ACCOUNT);

        Amount amount = new Amount();
        amount.setValue(BigDecimal.valueOf(10));
        amount.setCurrency(Currency.getInstance("EUR"));
        claim.setAmount(amount);

        claim.setContractual_item(ContractualItemEnum.CONTRACT_FOR_WORK);
        claim.setDue_date(LocalDateTime.of(2010,1,1,0,0,0));
        claim.setOccurrence_date(LocalDateTime.of(2010,1,1,0,0,0));
        claim.setSubject_matter("Order claim subject matter");
        claim.setYour_reference("Order claim reference");

        return claim;
    }



    public Credit createCredit() throws Exception {
        Credit credit = new Credit();
        credit.setValue_date(LocalDateTime.of(2010,1,1,0,0,0));

        Amount amount = new Amount();
        amount.setCurrency(Currency.getInstance("EUR"));
        amount.setValue(BigDecimal.valueOf(10));
        credit.setAmount(amount);


        ArrayList<PaymentCreditMetadata> metadataList = new ArrayList<PaymentCreditMetadata>(1);
        metadataList.add(createPaymentCreditMetadata());
        credit.setMetadata(metadataList);
        credit.setYour_reference("Order Credit Reference");
        return credit;
    }



    public ReceivableEvent createReceivableEvent() {
        ReceivableEvent eventInvoice = new ReceivableEvent();
        eventInvoice.setDescription("Description test");
        eventInvoice.setLocation("Location test");
        eventInvoice.setTitle("Title test");
        eventInvoice.setYour_reference("Reference test");
        eventInvoice.setType(ReceivableEvent.TypeEnum.INVOICE);
        eventInvoice.setOccurrence(LocalDateTime.of(2010, 1, 1, 0, 0, 0));
        return eventInvoice;
    }


    public PaymentCreditMetadata createPaymentCreditMetadata() {
        PaymentCreditMetadata claimMetaContract = new PaymentCreditMetadata();
        claimMetaContract.setType(PaymentCreditTypeEnum.CONTRACT_REF);
        claimMetaContract.setValue("Contract Metadata Test");
        return claimMetaContract;
    }



    public TransactionMetadata createTransactionMetadata() {
        TransactionMetadata claimMetaContract = new TransactionMetadata();
        claimMetaContract.setType(TransactionMetadata.TypeEnum.COMMENTS);
        claimMetaContract.setValue("Contract Metadata Test");
        return claimMetaContract;
    }




    public AdditionalCharge createAdditionalCharge() throws Exception {
        AdditionalCharge charge = new AdditionalCharge();
//        charge.setYour_claim_reference("Additional Charge claim ref");
        charge.setAdditional_charge_type(AdditionalChargeTypeEnum.ADVISORY_FEE);

        Amount amount = new Amount();
        amount.setCurrency(Currency.getInstance("EUR"));
        amount.setValue(BigDecimal.valueOf(10));
        charge.setAmount(amount);

        charge.setBeneficiary(BeneficiaryEnum.ACCOUNT);

        ArrayList<ReceivableEvent> eventList = new ArrayList<ReceivableEvent>(1);
        eventList.add(createReceivableEvent());
        charge.setEvents(eventList);

        ArrayList<TransactionMetadata> metadataList = new ArrayList<TransactionMetadata>(1);
        metadataList.add(createTransactionMetadata());
        charge.setMetadata(metadataList);

        charge.setOccurrence_date(LocalDateTime.of(2010,1,1,0,0,0));
        charge.setYour_reference("Order charge reference");
        return charge;

    }







    public Order createOrder() throws Exception {

        Order order = new Order();
        order.setQuality(QualityEnum.REGULAR);
        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        String custRefId = custRef.getId();

        order.setCustomer(custRefId);
        order.setCurrency(Currency.getInstance("EUR"));

        // order events
        ArrayList<OrderEvent> orderEvents = new ArrayList<OrderEvent>(1);
        orderEvents.add(createOrderEvent());
        order.setEvents(orderEvents);

        // order metadata
        ArrayList<OrderMetadata> orderMetadataArrayList = new ArrayList<OrderMetadata>(1);
        orderMetadataArrayList.add(createOrderMetadata());
        order.setMetadata(orderMetadataArrayList);

        // order claim
        ArrayList<Claim> claims = new ArrayList<Claim>(1);
        claims.add(createClaim());
        order.setPrincipal_claims(claims);

        // Order Credit
        ArrayList<Credit> credits = new ArrayList<Credit>(1);
        credits.add(createCredit());
        order.setCredits(credits);



        // Order Charge
        ArrayList<AdditionalCharge> charges = new ArrayList<AdditionalCharge>(1);
        charges.add(createAdditionalCharge());
        order.setAdditional_charges(charges);


        return order;
    }



    @Test
    public void OrderPostTest() throws Exception {
        Order order = createOrder();
        Order orderPosted = api.order.postOrder(order);


        Assert.assertTrue(order.getCustomer().equals(orderPosted.getCustomer()));
    }



}
