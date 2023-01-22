package org.ecollect.api.classes.notifications;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.classes.Amount;
import org.ecollect.api.classes.BeneficiaryEnum;
import org.ecollect.api.classes.InvoiceItem;
import org.ecollect.api.classes.TransactionMetadata;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.INotificationPayload;
import org.ecollect.api.serializers.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Currency;

public class NotInvoiceSubmitted implements INotificationPayload {

    private final String _notificationV2Type = "invoice.submitted";

    private String customer;
    private String your_reference;
    private Currency currency;


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime occurrence_date;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime due_date;

    private ArrayList<InvoiceItem> items;
    private ArrayList<TransactionMetadata> metadata;
    private BeneficiaryEnum beneficiary;
    private Amount amount;




    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getYour_reference() {
        return your_reference;
    }

    public void setYour_reference(String your_reference) {
        this.your_reference = your_reference;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getOccurrence_date() {
        return occurrence_date;
    }

    public void setOccurrence_date(LocalDateTime occurrence_date) {
        this.occurrence_date = occurrence_date;
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }

    public ArrayList<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    public ArrayList<TransactionMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(ArrayList<TransactionMetadata> metadata) {
        this.metadata = metadata;
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







    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }


    public String get_notificationV2Type() {
        return _notificationV2Type;
    }
}
