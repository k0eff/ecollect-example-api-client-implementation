package org.ecollect.api.apimodels;

import org.ecollect.api.classes.Claim;
import org.ecollect.api.classes.ClaimList;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class ClaimApiModel {

    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "claims";

    public ClaimApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }

    public Claim postClaim(Claim claim) throws EcollectAPIException, IOException {
        try {
            String claimJson = objectMapper.writeValueAsString(claim);
            String response = connection.submitPostRequest(baseUri, claimJson);
            return objectMapper.readValue(response, Claim.class);
        } catch (Exception e) {
            throw e;
        }
    }


    public ClaimList getManyClaims(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, ClaimList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public Claim getClaimByObj(Claim claim) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateClaimUri(claim);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Claim.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public Claim getClaimById(String claimId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateClaimUri(claimId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Claim.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }







    public String generateClaimUri(Claim claim) {
        return claim.getId() + "/";
    }

    public String generateClaimUri(String claimId) {
        return claimId + "/";
    }


}
