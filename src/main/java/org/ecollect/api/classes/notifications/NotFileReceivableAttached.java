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

public class NotFileReceivableAttached implements INotificationPayload {

    private final String _notificationV2Type = "file.receivableAttached";

    private String id;
    private ReceivableTypeEnum type;
    private BeneficiaryEnum beneficiary;
    private String your_reference;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime due_date;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime occurrence_date;

    private Amount amount;
    private String subject_matter;
    private AdditionalChargeTypeEnum additional_charge_type;
    private ContractualItemEnum contractual_item;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    @JsonGetter("type")
    public String getTypeJson() {
        return (type == null || type.equals("")) ? null : type.toString().toLowerCase();
    }

    public ReceivableTypeEnum getType() {
        return type;
    }

    @JsonSetter("type")
    public ReceivableTypeEnum setReceivableTypeEnumJson(String value) {
        if (value == null || value.equals(""))
            this.type=null;
        else
            this.type = ReceivableTypeEnum.fromValue(value);
        return this.type;
    }

    public void setType(ReceivableTypeEnum type) {
        this.type = type;
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









    @JsonGetter("additional_charge_type")
    public String getAdditionalChargeTypeEnumJson() {
        return (additional_charge_type == null || additional_charge_type.equals("")) ? null : additional_charge_type.toString().toLowerCase();
    }

    public AdditionalChargeTypeEnum getAdditionalChargeType() {
        return additional_charge_type;
    }

    @JsonSetter("additional_charge_type")
    public AdditionalChargeTypeEnum setAdditionalChargeTypeEnumJson(String value) {
        if (value == null || value.equals(""))
            this.additional_charge_type=null;
        else
            this.additional_charge_type = AdditionalChargeTypeEnum.fromValue(value);
        return this.additional_charge_type;
    }

    public void setAdditionalChargeType(AdditionalChargeTypeEnum additional_charge_type) {
        this.additional_charge_type = additional_charge_type;
    }










    @JsonGetter("contractual_item")
    public String getContractualItemEnumJson() {
        return (contractual_item == null || contractual_item.equals("")) ? null : contractual_item.toString().toLowerCase();
    }

    public ContractualItemEnum getContractualItem() {
        return contractual_item;
    }

    @JsonSetter("contractual_item")
    public ContractualItemEnum setContractualItemEnumJson(String value) {
        if (value == null || value.equals(""))
            this.contractual_item=null;
        else
            this.contractual_item = ContractualItemEnum.fromValue(value);
        return this.contractual_item;
    }

    public void setContractualItem(ContractualItemEnum type) {
        this.contractual_item = contractual_item;
    }











    public String getYour_reference() {
        return your_reference;
    }

    public void setYour_reference(String your_reference) {
        this.your_reference = your_reference;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public LocalDateTime getOccurrence_date() {
        return occurrence_date;
    }

    public void setOccurrence_date(LocalDateTime occurrence_date) {
        this.occurrence_date = occurrence_date;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getSubject_matter() {
        return subject_matter;
    }

    public void setSubject_matter(String subject_matter) {
        this.subject_matter = subject_matter;
    }

    public AdditionalChargeTypeEnum getAdditional_charge_type() {
        return additional_charge_type;
    }

    public void setAdditional_charge_type(AdditionalChargeTypeEnum additional_charge_type) {
        this.additional_charge_type = additional_charge_type;
    }

    public ContractualItemEnum getContractual_item() {
        return contractual_item;
    }

    public void setContractual_item(ContractualItemEnum contractual_item) {
        this.contractual_item = contractual_item;
    }


    public String get_notificationV2Type() {
        return _notificationV2Type;
    }
}
