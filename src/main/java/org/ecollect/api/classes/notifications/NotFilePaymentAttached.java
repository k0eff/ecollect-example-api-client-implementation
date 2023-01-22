package org.ecollect.api.classes.notifications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.classes.*;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.INotificationPayload;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class NotFilePaymentAttached implements INotificationPayload {

    private final String _notificationV2Type = "file.paymentAttached";
    private Amount amount;
    private PaymentTypeEnum type;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime value_date;


    private PspTypeEnum psp;
    private BeneficiaryEnum beneficiary;


    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }





    @JsonGetter("type")
    public String getTypeJson() {
        return (type == null || type.equals("")) ? null : type.toString().toLowerCase();
    }

    public PaymentTypeEnum getType() {
        return type;
    }

    @JsonSetter("type")
    public PaymentTypeEnum setPaymentTypeEnumJson(String value) {
        if (value == null || value.equals(""))
            this.type=null;
        else
            this.type = PaymentTypeEnum.fromValue(value);
        return this.type;
    }

    public void setType(PaymentTypeEnum type) {
        this.type = type;
    }






    public LocalDateTime getValue_date() {
        return value_date;
    }

    public void setValue_date(LocalDateTime value_date) {
        this.value_date = value_date;
    }



    @JsonGetter("beneficiary")
    public String getBeneficiaryEnumJson() {
        return (beneficiary == null || beneficiary.equals("")) ? null : beneficiary.toString().toLowerCase();
    }

    public BeneficiaryEnum getBeneficiary() {
        return beneficiary;
    }

    @JsonSetter("beneficiary")
    public BeneficiaryEnum setBeneficiaryEnumJson(String value) {
        if (value == null || value.equals(""))
            this.beneficiary=null;
        else
            this.beneficiary = BeneficiaryEnum.fromValue(value);
        return this.beneficiary;
    }

    public void setBeneficiary(BeneficiaryEnum beneficiary) {
        this.beneficiary = beneficiary;
    }







    @JsonGetter("psp")
    public String getPspTypeJson() {
        return (psp == null || psp.equals("")) ? null : psp.toString().toLowerCase();
    }

    public PspTypeEnum getPsp() {
        return psp;
    }

    @JsonSetter("psp")
    public PspTypeEnum setPspTypeEnumJson(String value) {
        if (value == null || value.equals(""))
            this.psp=null;
        else
            this.psp = PspTypeEnum.fromValue(value);
        return this.psp;
    }

    public void setPsp(PspTypeEnum psp) {
        this.psp = psp;
    }



    public String get_notificationV2Type() {
        return _notificationV2Type;
    }



}
