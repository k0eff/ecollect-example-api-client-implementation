package org.ecollect.api.classes.notifications;

import org.ecollect.api.classes.Communication;
import org.ecollect.api.interfaces.INotificationPayload;

import java.util.ArrayList;

public class NotFileCommunicationSent implements INotificationPayload {

    private final String _notificationV2Type = "file.communicationSent";
    private ArrayList<Communication> communications;


    public ArrayList<Communication> getCommunications() {
        return communications;
    }

    public void setCommunications(ArrayList<Communication> communications) {
        this.communications = communications;
    }

    public String get_notificationV2Type() {
        return _notificationV2Type;
    }
}
