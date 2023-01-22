package org.ecollect.api.classes.notifications;

import org.ecollect.api.interfaces.INotificationPayload;

public class NotFileStatusChanged implements INotificationPayload {

    private final String _notificationV2Type = "file.statusChanged";
    private String was;
    private String status;
    private String reason;


    public String get_notificationV2Type() {
        return _notificationV2Type;
    }

    public String getWas() {
        return was;
    }

    public void setWas(String was) {
        this.was = was;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
