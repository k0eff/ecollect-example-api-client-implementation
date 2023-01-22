package org.ecollect.api.classes.notifications;

import org.ecollect.api.classes.Valuta;
import org.ecollect.api.interfaces.INotificationPayload;

public class NotAccountBalanceUpdated  implements INotificationPayload {

    private final String _notificationV2Type = "account.balanceUpdated";

    private Valuta balance;

    public Valuta getBalance() {
        return balance;
    }

    public void setBalance(Valuta balance) {
        this.balance = balance;
    }


    public String get_notificationV2Type() {
        return _notificationV2Type;
    }

}
