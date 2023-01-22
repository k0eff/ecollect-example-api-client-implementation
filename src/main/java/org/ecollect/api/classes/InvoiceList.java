package org.ecollect.api.classes;

import java.util.ArrayList;

public class InvoiceList {
    private int total;
    private ArrayList<Invoice> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Invoice> getData() {
        return data;
    }

    public void setData(ArrayList<Invoice> data) {
        this.data = data;
    }


}
