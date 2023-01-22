package org.ecollect.api.classes;

import java.util.ArrayList;

public class NotificationV2List {


    private int total;
    private ArrayList<NotificationV2> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<NotificationV2> getData() {
        return data;
    }

    public void setData(ArrayList<NotificationV2> data) {
        this.data = data;
    }
}
