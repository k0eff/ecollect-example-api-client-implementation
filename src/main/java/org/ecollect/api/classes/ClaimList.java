package org.ecollect.api.classes;

import java.util.ArrayList;

public class ClaimList {
    private int total;
    private ArrayList<Claim> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Claim> getData() {
        return data;
    }

    public void setData(ArrayList<Claim> data) {
        this.data = data;
    }


}
