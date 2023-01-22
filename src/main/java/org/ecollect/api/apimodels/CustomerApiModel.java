package org.ecollect.api.apimodels;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.http.entity.mime.content.StringBody;
import org.ecollect.api.classes.*;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class CustomerApiModel {

    private Connection connection;
    private EcollectObjectMapper objectMapper;



    private final String baseUri = "customers";


    public CustomerApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }


    public Customer postCustomer(Customer cust) throws EcollectAPIException, IOException {
        try {
        String custJson = objectMapper.writeValueAsString(cust);
        String response = connection.submitPostRequest(baseUri, custJson);
            return objectMapper.readValue(response, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }

    public CustomerList getManyCustomers(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = getBaseUriSlashed() + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, CustomerList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e; // TODO: handle with Ecollect error
        }
    }


    public Customer getCustomerByObj(Customer cust) throws EcollectAPIException, IOException {
        try {
            String uri = getBaseUriSlashed() + this.generateCustomerUri(cust);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Customer.class);
        } catch (EcollectAPIException e) {
            throw e; // TODO: handle errors
        }
    }

    public Customer getCustomerById(String cust) throws IOException, EcollectAPIException {
        try {
            String uri = getBaseUriSlashed() + this.generateCustomerUri(cust);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Customer.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }



    public Customer addContact(Customer cust, Contact contact) throws IOException, EcollectAPIException {
        try {
            String fullUri = getBaseUriSlashed() + generateCustomerContactUri(cust);
            String json = objectMapper.writeValueAsString(contact);
            String res = connection.submitPostRequest(fullUri, json);
            return objectMapper.readValue(res, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }



    public Customer addAddress(Customer cust, Address address) throws IOException, EcollectAPIException {
        try {
            String fullUri = getBaseUriSlashed() + generateCustomerAddressUri(cust);
            String json = objectMapper.writeValueAsString(address);
            String res = connection.submitPostRequest(fullUri, json);
            return objectMapper.readValue(res, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }




    public Customer addMetadata(Customer cust, CustomerMetadata metadata) throws IOException, EcollectAPIException {
        try {
            String fullUri = getBaseUriSlashed() + generateCustomerMetadataUri(cust);
            String json = objectMapper.writeValueAsString(metadata);
            String res = connection.submitPostRequest(fullUri, json);
            return objectMapper.readValue(res, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }




    public Customer addEvent(Customer cust, CustomerEvent event) throws IOException, EcollectAPIException {
        try {
            String fullUri = getBaseUriSlashed() + generateCustomerEventUri(cust);
            String json = objectMapper.writeValueAsString(event);
            String res = connection.submitPostRequest(fullUri, json);
            return objectMapper.readValue(res, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }



    public Customer addBankAccount(Customer cust, BankAccount bankAccount) throws IOException, EcollectAPIException {
        try {
            String fullUri = getBaseUriSlashed() + generateCustomerBankAccountUri(cust);
            String json = objectMapper.writeValueAsString(bankAccount);
            String res = connection.submitPostRequest(fullUri, json);
            return objectMapper.readValue(res, Customer.class);
        } catch (Exception e) {
            throw e;
        }
    }


    // TODO: add support for relations





    public String generateCustomerUri(Customer cust) {
        return cust.getId() + "/";
    }

    public String generateCustomerUri(String custId) {
        return custId + "/";
    }


    public String generateCustomerContactUri(Customer cust) {
        return this.generateCustomerInnerUri(cust, "contact");
    }
    public String generateCustomerAddressUri(Customer cust) {
        return this.generateCustomerInnerUri(cust, "address");
    }
    public String generateCustomerMetadataUri(Customer cust) {
        return this.generateCustomerInnerUri(cust, "metadata");
    }
    public String generateCustomerEventUri(Customer cust) {
        return this.generateCustomerInnerUri(cust, "event");
    }
    public String generateCustomerBankAccountUri(Customer cust) {
        return this.generateCustomerInnerUri(cust, "bank");
    }
// // TODO: implement Relations
    //    public String generateCustomerRelationUri(Customer cust) {
//        return this.generateCustomerInnerUri(cust, "metadata");
//
//    }



    private String generateCustomerInnerUri(Customer cust, String innerObj) {
        return cust.getId() + "/" + innerObj + "/";
    }

    private String getBaseUriSlashed() {
        return this.baseUri + "/";
    }


}
