package org.ecollect.api.deserializers;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.ecollect.api.classes.NotificationV2;
import org.ecollect.api.classes.Regarding;
import org.ecollect.api.classes.notifications.*;
import org.ecollect.api.interfaces.INotificationPayload;

import java.io.IOException;
import java.time.LocalDateTime;

public class NotificationV2Deserializer extends StdDeserializer {


    static final String DefFileStatusChanged = "file.statusChanged";
    static final String DefAccountBalanceUpdated = "account.balanceUpdated";
    static final String DefFileCommunicationSent = "file.communicationSent";
    static final String DefFileInvoiceAttached = "file.invoiceAttached";
    static final String DefFilePaymentAttached = "file.paymentAttached";
    static final String DefFileReceivableAttached = "file.receivableAttached";
    static final String DefFileStageChanged = "file.stageChanged";
    static final String DefInvoiceAssociatedWithFile = "invoice.associatedWithFile";
    static final String DefInvoiceSubmitted = "invoice.submitted";



    public NotificationV2Deserializer() {
        this(null);
    }


    public NotificationV2Deserializer(Class<?> vc) {
        super(vc);
    }


    @Override
    public NotificationV2 deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        try {
            JsonNode node = p.getCodec().readTree(p);
            JsonParser nodeParser = node.traverse();
            nodeParser.setCodec(p.getCodec());


            String id = node.get("id").asText();
            String aggId = node.get("aggId").asText();
            String type = node.get("type").asText();
            String version = node.get("version").asText();
            JsonNode regarding = node.get("regarding");
            JsonNode created_atJson = node.get("created_at");
            JsonNode payload = node.get("payload");

            NotificationV2 notificationV2 = new NotificationV2();
            notificationV2.setId(id);
            notificationV2.setAggId(aggId);
            notificationV2.setType(type);
            notificationV2.setVersion(version);


            // use custom deserializer for created_at
            ObjectMapper om = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
            om.registerModule(module);
            LocalDateTime created_at = om.readValue(created_atJson.toString(), LocalDateTime.class);


            // deserialize regarding
            JsonParser regardingParser = regarding.traverse();
            regardingParser.setCodec(p.getCodec());
            Regarding regardingObj = (Regarding) regardingParser.readValueAs(Regarding.class);
            notificationV2.setRegarding(regardingObj);

            // deserialize payload
            JsonParser payloadParser = payload.traverse();
            payloadParser.setCodec(p.getCodec());

            INotificationPayload notData;

            if (type.equals(DefFileStatusChanged)) {
                notData = (NotFileStatusChanged) payloadParser.readValueAs(NotFileStatusChanged.class);
            } else if (type.equals(DefAccountBalanceUpdated)) {
                notData = (NotAccountBalanceUpdated) payloadParser.readValueAs(NotAccountBalanceUpdated.class);
            } else if (type.equals(DefFileCommunicationSent)) {
                notData = (NotFileCommunicationSent) payloadParser.readValueAs(NotFileCommunicationSent.class);
            } else if (type.equals(DefFileInvoiceAttached)) {
                notData = (NotFileInvoiceAttached) payloadParser.readValueAs(NotFileInvoiceAttached.class);
            } else if (type.equals(DefFilePaymentAttached)) {
                notData = (NotFilePaymentAttached) payloadParser.readValueAs(NotFilePaymentAttached.class);
            } else if (type.equals(DefFileReceivableAttached)) {
                notData = (NotFileReceivableAttached) payloadParser.readValueAs(NotFileReceivableAttached.class);
            } else if (type.equals(DefFileStageChanged)) {
                notData = (NotFileStageChanged) payloadParser.readValueAs(NotFileStageChanged.class);
            } else if (type.equals(DefInvoiceAssociatedWithFile)) {
                notData = (NotInvoiceAssociatedWithFile) payloadParser.readValueAs(NotInvoiceAssociatedWithFile.class);
            } else if (type.equals(DefInvoiceSubmitted)) {
                notData = (NotInvoiceSubmitted) payloadParser.readValueAs(NotInvoiceSubmitted.class);
            } else {
                throw new IOException("NotificationV2 Deserializer error: Cannot handle notification because of unknown type: " + type.toString() + ". ");
            }
            notificationV2.setPayload(notData);
            return notificationV2;
        } catch (Exception e) {throw e;}
    }
}
