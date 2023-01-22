package org.ecollect.api.apimodels;

public class NotificationApiModelFilters {

    String regardingCustomer;
    String regardingFile;
    Boolean undelivered;


    public String getRegardingCustomer() {
        return regardingCustomer;
    }


    public void setRegardingCustomer(String regardingCustomer) {
        this.regardingCustomer = regardingCustomer;
    }


    public String getRegardingFile() {
        return regardingFile;
    }


    public void setRegardingFile(String regardingFile) {
        this.regardingFile = regardingFile;
    }


    public Boolean getUndelivered() {
        return undelivered;
    }


    public void setUndelivered(Boolean undelivered) {
        this.undelivered = undelivered;
    }



}
