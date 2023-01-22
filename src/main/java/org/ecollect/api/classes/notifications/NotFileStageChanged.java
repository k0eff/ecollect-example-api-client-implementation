package org.ecollect.api.classes.notifications;

import org.ecollect.api.classes.StageEnum;
import org.ecollect.api.interfaces.INotificationPayload;

public class NotFileStageChanged  implements INotificationPayload {

    private final String _notificationV2Type = "file.stageChanged";

    private StageEnum was;
    private StageEnum stage;


    public StageEnum getWas() {
        return was;
    }

    public void setWas(StageEnum was) {
        this.was = was;
    }

    public StageEnum getStage() {
        return stage;
    }

    public void setStage(StageEnum stage) {
        this.stage = stage;
    }

    public String get_notificationV2Type() {
        return _notificationV2Type;
    }
}
