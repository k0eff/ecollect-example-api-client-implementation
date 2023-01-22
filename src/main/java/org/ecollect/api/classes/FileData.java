package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.ecollect.api.interfaces.IReceivableType;

public class FileData implements IReceivableType {

    private Claim claim;
    private Credit credit;
    private AdditionalCharge charge;
//    private Payment payment;  //TODO: implement payments

    private ReceivableTypeEnum type;


    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public AdditionalCharge getCharge() {
        return charge;
    }

    public void setCharge(AdditionalCharge charge) {
        this.charge = charge;
    }

    public ReceivableTypeEnum getType() {
        return type;
    }

    @JsonGetter("type")
    public String getTypeJson() {
        return (type != null) ? type.toString().toLowerCase() : null;
    }

    public void setType(ReceivableTypeEnum type) {
        this.type = type;
    }

    @JsonSetter("type")
    public ReceivableTypeEnum setTypeEnum(String value) {
        this.type = ReceivableTypeEnum.fromValue(value);
        return this.type;
    }



}
