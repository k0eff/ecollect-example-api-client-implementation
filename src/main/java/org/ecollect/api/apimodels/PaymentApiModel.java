package org.ecollect.api.apimodels;

import org.ecollect.api.classes.Payment;
import org.ecollect.api.classes.PaymentList;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class PaymentApiModel {

    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "payments";

    public PaymentApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }

    public Payment postPayment(Payment payment) throws EcollectAPIException, IOException {
        try {
            String paymentJson = objectMapper.writeValueAsString(payment);
            String response = connection.submitPostRequest(baseUri, paymentJson);
            return objectMapper.readValue(response, Payment.class);
        } catch (Exception e) {
            throw e;
        }
    }


    public PaymentList getManyPayments(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, PaymentList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public Payment getPaymentByObj(Payment payment) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generatePaymentUri(payment);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Payment.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public Payment getPaymentById(String paymentId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generatePaymentUri(paymentId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Payment.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }







    public String generatePaymentUri(Payment payment) {
        return payment.getId() + "/";
    }

    public String generatePaymentUri(String paymentId) {
        return paymentId + "/";
    }


}
