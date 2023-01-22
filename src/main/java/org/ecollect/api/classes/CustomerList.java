package org.ecollect.api.classes;

import java.util.ArrayList;

public class CustomerList {
    private int total;
    private ArrayList<Customer> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Customer> getData() {
        return data;
    }

    public void setData(ArrayList<Customer> data) {
        this.data = data;
    }


}
