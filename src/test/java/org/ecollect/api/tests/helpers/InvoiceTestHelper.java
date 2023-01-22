package org.ecollect.api.tests.helpers;

import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class InvoiceTestHelper {

    public static Invoice createInvoice(Customer custRef) throws Exception {
        // invoice metadata

        TransactionMetadata invoiceMetaContract = new TransactionMetadata();
        invoiceMetaContract.setType(TransactionMetadata.TypeEnum.CONTRACT_REFERENCE);
        invoiceMetaContract.setValue("Contract Metadata Test");

        TransactionMetadata invoiceMetaInv = new TransactionMetadata();
        invoiceMetaInv.setType(TransactionMetadata.TypeEnum.INVOICE_REFERENCE);
        invoiceMetaInv.setValue("Invoice Metadata Test");

        ArrayList<TransactionMetadata> invoiceMetadata = new ArrayList<>(1);
        invoiceMetadata.add(invoiceMetaContract);
        invoiceMetadata.add(invoiceMetaInv);


        // Tax

        Tax tax = new Tax();
        tax.setType(Tax.TaxTypeEnum.VAT);
        tax.setPercent(BigDecimal.valueOf(20));


        // invoice items

        InvoiceItem item1 = new InvoiceItem();
        item1.setSubject_matter("Subject Matter Test  ä, ö, ü, ß");
        item1.setContractual_item(ContractualItemEnum.CONTRACT_FOR_WORK);
        item1.setUnit_type(InvoiceItem.UnitTypeEnum.HOUR);
        item1.setQuantity(BigInteger.valueOf(100));
        item1.setUnit_price(BigDecimal.valueOf(10.5));
        item1.setTax(tax);

        InvoiceItem item2 = new InvoiceItem();
        item2.setSubject_matter("Subject Matter Test 2");
        item2.setContractual_item(ContractualItemEnum.CONTRACT_FOR_WORK);
        item2.setUnit_type(InvoiceItem.UnitTypeEnum.HOUR);
        item2.setQuantity(BigInteger.valueOf(200));
        item2.setUnit_price(BigDecimal.valueOf(22.5));
        item2.setTax(tax);

        ArrayList<InvoiceItem> invoiceItems = new ArrayList<>(2);
        invoiceItems.add(item1);
        invoiceItems.add(item2);


        // invoice

        Invoice invoice = new Invoice();
        invoice.setCurrency(Currency.getInstance("EUR"));
        invoice.setYour_reference("Your Reference Test");

        String custRefId = custRef.getId();

        invoice.setCustomer(custRefId);
        invoice.setOccurrence_date(LocalDateTime.of(2010,1,1,0,0,0));
        invoice.setDue_date(LocalDateTime.of(2010,1,1,0,0,0));
        invoice.setMetadata(invoiceMetadata);
        invoice.setItems(invoiceItems);


        return invoice;
    }


    public static Invoice postInvoice(EcollectAPIHandler api, Invoice invoice) throws EcollectAPIException, IOException {
        return api.invoice.postInvoice(invoice);
    }

    public static Invoice getInvoiceSingle(EcollectAPIHandler api, Invoice invoice) throws EcollectAPIException, IOException {
            return api.invoice.getInvoiceByObj(invoice);
    }

    public static InvoiceList getInvoiceList(EcollectAPIHandler api) throws EcollectAPIException, IOException {
        return api.invoice.getManyInvoices(2,0);
    }


}
