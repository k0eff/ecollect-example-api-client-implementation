package org.ecollect.api.apimodels;

import org.ecollect.api.classes.Order;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;

public class OrderApiModel {


    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "orders";

    public OrderApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }


    public Order postOrder(Order Order) throws EcollectAPIException, IOException {
        try {
            String OrderJson = objectMapper.writeValueAsString(Order);
            String response = connection.submitPostRequest(baseUri, OrderJson);
            return objectMapper.readValue(response, Order.class);
        } catch (Exception e) {
            throw e;
        }
    }



}
