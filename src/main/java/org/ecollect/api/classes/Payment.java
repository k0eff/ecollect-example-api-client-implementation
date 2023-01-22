package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.ecollect.api.abstractClasses.AConciliation;
import org.ecollect.api.interfaces.IConciliation;
import org.ecollect.api.utils.IdDenormalizer;

public class Payment extends AConciliation implements IConciliation {

    @JsonIgnore
    private final String type = "payment";


    private String id;  // property is denormalized

    private PayeeEnum payee;



    @JsonCreator
    public Payment() throws Exception {
    }






    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public PayeeEnum getPayee() {
        return payee;
    }

    @JsonGetter("payee")
    public String getPayeeJSON() {
        return (payee != null) ? payee.toString().toLowerCase() : null;
    }

    public void setPayee(PayeeEnum payee) {
        this.payee = payee;
    }

    @JsonSetter("payee")
    public PayeeEnum setPayeeJSON(String value) {
        this.payee = PayeeEnum.fromValue(value);
        return this.payee;
    }


    @Override
    public String getType() {
        return type;
    }




}
