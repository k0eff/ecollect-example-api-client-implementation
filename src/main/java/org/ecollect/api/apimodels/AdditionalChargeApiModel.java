package org.ecollect.api.apimodels;

import org.ecollect.api.classes.AdditionalCharge;
import org.ecollect.api.classes.AdditionalChargeList;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class AdditionalChargeApiModel {


    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "charges";

    public AdditionalChargeApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }


    public AdditionalCharge postCharge(AdditionalCharge charge) throws EcollectAPIException, IOException {
        try {
            String claimJson = objectMapper.writeValueAsString(charge);
            String response = connection.submitPostRequest(baseUri, claimJson);
            return objectMapper.readValue(response, AdditionalCharge.class);
        } catch (Exception e) {
            throw e;
        }
    }


    public AdditionalChargeList getManyCharges(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, AdditionalChargeList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }



    public AdditionalCharge getChargeByObj(AdditionalCharge charge) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateChargeUri(charge);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, AdditionalCharge.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public AdditionalCharge getChargeById(String chargeId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateChargeUri(chargeId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, AdditionalCharge.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }


    public String generateChargeUri(AdditionalCharge charge) {
        return charge.getId() + "/";
    }

    public String generateChargeUri(String chargeId) {
        return chargeId + "/";
    }






}
