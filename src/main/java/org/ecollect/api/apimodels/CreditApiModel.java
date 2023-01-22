package org.ecollect.api.apimodels;

import org.ecollect.api.classes.Credit;
import org.ecollect.api.classes.CreditList;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class CreditApiModel {

    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "credits";

    public CreditApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }

    public Credit postCredit(Credit credit) throws EcollectAPIException, IOException {
        try {
            String creditJson = objectMapper.writeValueAsString(credit);
            String response = connection.submitPostRequest(baseUri, creditJson);
            return objectMapper.readValue(response, Credit.class);
        } catch (Exception e) {
            throw e;
        }
    }


    public CreditList getManyCredits(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, CreditList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public Credit getCreditByObj(Credit credit) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateCreditUri(credit);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Credit.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public Credit getCreditById(String creditId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateCreditUri(creditId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Credit.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }







    public String generateCreditUri(Credit credit) {
        return credit.getId() + "/";
    }

    public String generateCreditUri(String creditId) {
        return creditId + "/";
    }


}
