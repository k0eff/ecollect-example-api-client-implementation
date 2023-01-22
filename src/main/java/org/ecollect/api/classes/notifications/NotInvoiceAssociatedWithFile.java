package org.ecollect.api.classes.notifications;

import org.ecollect.api.interfaces.INotificationPayload;

public class NotInvoiceAssociatedWithFile implements INotificationPayload {

    private final String _notificationV2Type = "invoice.associatedWithFile";
    private String was;
    private String file;


    public String get_notificationV2Type() {
        return _notificationV2Type;
    }

    public String getWas() {
        return was;
    }

    public void setWas(String was) {
        this.was = was;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

}
