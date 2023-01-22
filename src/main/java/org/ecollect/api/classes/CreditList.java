package org.ecollect.api.classes;

import java.util.ArrayList;

public class CreditList {
    private int total;
    private ArrayList<Credit> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Credit> getData() {
        return data;
    }

    public void setData(ArrayList<Credit> data) {
        this.data = data;
    }


}
