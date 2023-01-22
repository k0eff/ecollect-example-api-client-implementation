package org.ecollect.api.classes;

import java.util.ArrayList;

public class PaymentList {
    private int total;
    private ArrayList<Payment> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Payment> getData() {
        return data;
    }

    public void setData(ArrayList<Payment> data) {
        this.data = data;
    }


}
