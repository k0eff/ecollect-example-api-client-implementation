package org.ecollect.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ecollect.api.apimodels.*;
import org.ecollect.api.utils.ApiSettings;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;
import org.ecollect.api.utils.IdDenormalizer;

public class EcollectAPIHandler {

    private final boolean IdDenormalizerActiveState = true;


    private ApiSettings apiSettings;
    private Connection connection;
    private ObjectMapper objectMapper;
    private EcollectObjectMapper ecollectObjectMapper;
    private IdDenormalizer idDenormalizerInstance;


    public CustomerApiModel customer;
    public ClaimApiModel claim;
    public AdditionalChargeApiModel charge;
    public CreditApiModel credit;
    public InvoiceApiModel invoice;
    public OrderApiModel order;
    public FileApiModel file;
    public DocumentApiModel document;
    public PaymentApiModel payment;
    public NotificationApiModel notification;

    public EcollectAPIHandler(String authToken, String urlString, int timeout) {
        apiSettings = new ApiSettings(authToken, urlString, timeout);
        connection = new Connection(apiSettings);
        objectMapper = new ObjectMapper();
        ecollectObjectMapper = new EcollectObjectMapper(objectMapper);
        idDenormalizerInstance = IdDenormalizer.buildSingleton(true);



        customer = new CustomerApiModel(connection, ecollectObjectMapper );
        claim = new ClaimApiModel(connection, ecollectObjectMapper);
        charge = new AdditionalChargeApiModel(connection, ecollectObjectMapper);
        credit = new CreditApiModel(connection, ecollectObjectMapper);
        invoice = new InvoiceApiModel(connection, ecollectObjectMapper);
        order = new OrderApiModel(connection, ecollectObjectMapper);
        file = new FileApiModel(connection, ecollectObjectMapper);
        document = new DocumentApiModel(connection, ecollectObjectMapper);
        payment = new PaymentApiModel(connection, ecollectObjectMapper);
        notification = new NotificationApiModel(connection, ecollectObjectMapper);
    }

}
