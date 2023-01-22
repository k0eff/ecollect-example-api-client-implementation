package org.ecollect.api.apimodels;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecollect.api.classes.NotificationV2;
import org.ecollect.api.classes.NotificationV2List;
import org.ecollect.api.classes.NotificationsDelivered;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class NotificationApiModel {

    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "notifications";

    public NotificationApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }


    public NotificationV2List getManyNotifications(int size, int from, NotificationApiModelFilters filters) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String uriWithFilters = buildUriWithFilters(uri, filters);

            String resp = connection.submitGetRequest(uriWithFilters);
            return objectMapper.readValue(resp, NotificationV2List.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public NotificationV2 getNotificationById(String notificationId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateNotificationUri(notificationId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, NotificationV2.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }

    public NotificationsDelivered markNotificationsDelivered(NotificationsDelivered notDel) throws IOException, EcollectAPIException {
        String jsonData = objectMapper.writeValueAsString(notDel);
        try {
            String uri = baseUri + "/";
            String resp = connection.submitPatchRequest(uri, jsonData);
            return objectMapper.readValue(resp, NotificationsDelivered.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }






    public String generateNotificationUri(String notificationId) {
        return notificationId + "/";
    }


    private String buildUriWithFilters(String uri, NotificationApiModelFilters filters) throws UnsupportedEncodingException {
        String newUri = uri;
        if (filters.getUndelivered()!= null && filters.getUndelivered().equals(true)) newUri += "&undelivered=t";
        if (filters.getRegardingCustomer() != null && filters.getRegardingCustomer().length()>0) newUri += "&regarding[customer]=" + urlEnc(filters.getRegardingCustomer());
        if (filters.getRegardingFile() != null && filters.getRegardingFile().length()> 0) newUri += "&regarding[file]=" + urlEnc(filters.getRegardingFile());

        return newUri;
    }


    private String urlEnc(String u) throws UnsupportedEncodingException {
        return URLEncoder.encode(u, StandardCharsets.UTF_8.toString());
    }
}
