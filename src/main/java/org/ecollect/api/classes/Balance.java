package org.ecollect.api.classes;

public class Balance {


    private Amount current;
    private Amount paid;
    private Amount total;



    public Amount getCurrent() {
        return current;
    }

    public void setCurrent(Amount amount) {
        this.current = amount;
    }



    public Amount getPaid() {
        return paid;
    }

    public void setPaid(Amount amount) {
        this.paid = amount;
    }


    public Amount getTotal() {
        return total;
    }

    public void setTotal(Amount amount) {
        this.total = amount;
    }

}
