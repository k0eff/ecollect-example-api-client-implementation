package org.ecollect.api.classes;

import java.util.ArrayList;

public class FileList {

    private int total;
    private ArrayList<File> data;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<File> getData() {
        return data;
    }

    public void setData(ArrayList<File> data) {
        this.data = data;
    }
}
