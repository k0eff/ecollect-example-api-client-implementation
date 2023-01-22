package org.ecollect.api.tests;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.apimodels.NotificationApiModelFilters;
import org.ecollect.api.classes.*;
import org.ecollect.api.classes.notifications.NotFileInvoiceAttached;
import org.ecollect.api.classes.notifications.NotFilePaymentAttached;
import org.ecollect.api.classes.notifications.NotFileReceivableAttached;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class NotificationTest {

    private final int notListGetSize = 100;
    private EcollectAPIHandler api;
    private Customer custPosted;
    private Invoice invoice;
    private Invoice invoicePosted;
    private NotificationV2List notL;
    private Payment paymentPosted;
    private NotificationV2List notLPayment;
    private AdditionalCharge chargePosted;
    private NotificationV2List notLCharge;

    /**
     * 1. Create Customer
     * 2. Create Invoice
     * 2.1. Receive notification invoice.submitted
     * 2.2. Receive notification invoice.associatedWithFile
     * 2.3. Receive notification file.invoiceAttached
     * 3. Create Direct payment for invoice
     * 3.1. Receive notification file.paymentAttached
     * 4. Create Additional Charge for file
     * 4.1. Receive notification file.receivableAttached
     *
     */





    public NotificationTest() {
        api = ApiHandlerHelper.setupApi();
    }


    private String stripDashes(String str) {
        return str.split("-").toString();
    }

    private String stripIdPrefix(String str) {
        return str.substring(4);
    }


    @Before
    public void createEnvironment() throws Exception {
        custPosted = CustomerTestHelper.createCustomerOrg(api);

        invoice = InvoiceTestHelper.createInvoice(custPosted);
        invoicePosted = InvoiceTestHelper.postInvoice(api, invoice);
        TimeUnit.SECONDS.sleep(15); // Due to many calculations sagas are pretty slow, so please wait for a while
        NotificationApiModelFilters notificationApiModelFiltersEmpty = new NotificationApiModelFilters(); // no filters really

        notL = api.notification.getManyNotifications(notListGetSize, 0, notificationApiModelFiltersEmpty);



        Payment payment = PaymentTestHelper.createPaymentWithInvoice(custPosted, invoicePosted);
        paymentPosted = PaymentTestHelper.postPayment(api, payment);
        TimeUnit.SECONDS.sleep(15); // Due to many calculations sagas are pretty slow, so please wait for a while
        notLPayment = api.notification.getManyNotifications(notListGetSize, 0, notificationApiModelFiltersEmpty);



        AdditionalCharge charge = AdditionalChargeTestHelper.createCharge(custPosted, null);
        chargePosted = AdditionalChargeTestHelper.postCharge(api, charge);
        TimeUnit.SECONDS.sleep(15); // Due to many calculations sagas are pretty slow, so please wait for a while
        notLCharge = api.notification.getManyNotifications(notListGetSize, 0, notificationApiModelFiltersEmpty);


    }



    @Test
    public void checkInvoiceSubmitted() throws IOException, EcollectAPIException {

        boolean invoiceSubmittedIsContainedInList = false;

        for (NotificationV2 not : notL.getData()) {

            if (not.getType().equals("invoice.submitted")) {
                String nv2 = not.getAggId().substring(4);
                String invP = invoicePosted.getId().substring(4);
                if (nv2.equals(invP)) {invoiceSubmittedIsContainedInList = true;}
            }
        }

        Assert.assertTrue(invoiceSubmittedIsContainedInList);
    }

    @Test
    public void checkInvoiceAssociatedWithFile() throws IOException, EcollectAPIException {

        boolean invoiceAssociatedWithFileIsContainedInList = false;

        for (NotificationV2 not : notL.getData()) {
            if (not.getType().equals("invoice.associatedWithFile")
                    && not.getAggId().substring(4).equals(invoicePosted.getId().substring(4)) // remove "inv-"
            ) invoiceAssociatedWithFileIsContainedInList = true;
        }

        Assert.assertTrue(invoiceAssociatedWithFileIsContainedInList);
    }


    private BigDecimal calcWithTax(BigDecimal unitPrice, BigInteger qty, BigDecimal tax) {
        return unitPrice
                .multiply(new BigDecimal(qty))
                .multiply(
                        BigDecimal.valueOf(1).add(
                                tax.divide(BigDecimal.valueOf(100))
                        )
                );
    }


    @Test
    public void checkFileInvoiceAttached() throws IOException, EcollectAPIException {

        boolean fileInvoiceAttachedIsContainedInList = false;



        for (NotificationV2 not : notL.getData()) {

            if (not.getType().equals("file.invoiceAttached")) {

                NotFileInvoiceAttached nfia = (NotFileInvoiceAttached) not.getPayload();
                String notId = nfia.getId();

                if (notId.equals(invoicePosted.getId())) {
                    BigDecimal nAmt = BigDecimal.valueOf(0);
                    BigDecimal iAmt = BigDecimal.valueOf(0);


                    NotFileInvoiceAttached nia = (NotFileInvoiceAttached) not.getPayload();
                    ArrayList<InvoiceItem> invI = nia.getItems();
                    for (InvoiceItem item : invI) {

                        nAmt = nAmt.add(calcWithTax(item.getUnit_price(), item.getQuantity(), item.getTax().getPercent()));

                    }

                    ArrayList<InvoiceItem> invPI = invoicePosted.getItems();
                    for (InvoiceItem item : invPI) {
                        iAmt = iAmt.add(calcWithTax(item.getUnit_price(), item.getQuantity(), item.getTax().getPercent()));
                    }



                    if (nAmt.equals(iAmt)) {
                        fileInvoiceAttachedIsContainedInList = true;
                    }
                }

            }
        }

        Assert.assertTrue(fileInvoiceAttachedIsContainedInList);
    }


    @Test
    public void checkFilePaymentAttached() throws IOException, EcollectAPIException {

        boolean filePaymentAttachedIsContainedInList = false;

        for (NotificationV2 not : notLPayment.getData()) {
            if (not.getType().equals("file.paymentAttached")) {
                NotFilePaymentAttached npa = (NotFilePaymentAttached) not.getPayload();
                BigDecimal nval = npa.getAmount().getValue();
                BigDecimal pval = paymentPosted.getAmount().getValue();

                if (pval.equals(nval)) {filePaymentAttachedIsContainedInList = true;}
            }
        }
        Assert.assertTrue(filePaymentAttachedIsContainedInList);
    }


    @Test
    public void checkFileReceivableAttached() throws IOException, EcollectAPIException {

        boolean fileReceivableAttachedIsContainedInList = false;

        for (NotificationV2 not : notLPayment.getData()) {
            if (not.getType().equals("file.receivableAttached")) {
                NotFileReceivableAttached nra = (NotFileReceivableAttached) not.getPayload();
                BigDecimal nAmt = nra.getAmount().getValue();

                BigDecimal pPAmt = paymentPosted.getAmount().getValue();

                if (nAmt.equals(pPAmt)) fileReceivableAttachedIsContainedInList = true;
            }
        }

        Assert.assertTrue(fileReceivableAttachedIsContainedInList);
    }
}
