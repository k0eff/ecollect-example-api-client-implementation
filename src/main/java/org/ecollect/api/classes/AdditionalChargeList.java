
package org.ecollect.api.classes;

import java.util.ArrayList;

public class AdditionalChargeList {
    private int total;
    private ArrayList<AdditionalCharge> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<AdditionalCharge> getData() {
        return data;
    }

    public void setData(ArrayList<AdditionalCharge> data) {
        this.data = data;
    }


}
