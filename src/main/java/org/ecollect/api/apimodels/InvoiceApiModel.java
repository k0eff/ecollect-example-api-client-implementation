package org.ecollect.api.apimodels;

import org.ecollect.api.classes.Invoice;
import org.ecollect.api.classes.InvoiceList;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class InvoiceApiModel {



    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "invoices";

    public InvoiceApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }


    public Invoice postInvoice(Invoice invoice) throws EcollectAPIException, IOException {
        try {
            String invoiceJson = objectMapper.writeValueAsString(invoice);
            String response = connection.submitPostRequest(baseUri, invoiceJson);
            return objectMapper.readValue(response, Invoice.class);
        } catch (Exception e) {
            throw e;
        }
    }


    public InvoiceList getManyInvoices(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, InvoiceList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public Invoice getInvoiceByObj(Invoice invoice) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateInvoiceUri(invoice);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Invoice.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public Invoice getInvoiceById(String invoiceId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateInvoiceUri(invoiceId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Invoice.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }








    public String generateInvoiceUri(Invoice invoice) {
        return invoice.getId() + "/";
    }

    public String generateInvoiceUri(String invoiceId) {
        return invoiceId + "/";
    }






}
