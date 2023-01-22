package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.*;
import org.ecollect.api.abstractClasses.AReceivable;
import org.ecollect.api.interfaces.IReceivable;
import org.ecollect.api.utils.IdDenormalizer;


@JsonIgnoreProperties(value = { "additional_charge_source", "acting_as" })
public class AdditionalCharge extends AReceivable implements IReceivable {

    @JsonIgnore
    private final String type = "charge";


    private String id;  // property is denormalized

    private String your_claim_reference;



    @JsonProperty(required = true)
    private AdditionalChargeTypeEnum additional_charge_type;



    @JsonCreator
    public AdditionalCharge() throws Exception {
        this.idDenormalizer = IdDenormalizer.getInstance();
    }





    private String normalizeChargeId(String oldId) {
        return oldId != null ? this.idDenormalizer.charge(oldId) : null;
    }

    public String getId() {
        return this.normalizeChargeId(id);
    }

    public void setId(String id) {
        this.id = this.normalizeChargeId(id);
    }


    @Override
    public String getType() {
        return type;
    }




    public AdditionalChargeTypeEnum getAdditional_charge_type() {
        return additional_charge_type;
    }

    @JsonGetter("additional_charge_type")
    public String getAdditional_charge_typeJSON() {
        return (additional_charge_type != null) ? additional_charge_type.toString().toLowerCase() : null;
    }

    public void setAdditional_charge_type(AdditionalChargeTypeEnum val) {
        this.additional_charge_type = val;
    }

    @JsonSetter("additional_charge_type")
    public AdditionalChargeTypeEnum setAdditional_charge_typeJSON(String value) {
        this.additional_charge_type = AdditionalChargeTypeEnum.fromValue(value);
        return this.additional_charge_type;
    }




    public String getYour_claim_reference() {
        return your_claim_reference;
    }

    public void setYour_claim_reference(String your_claim_reference) {
        this.your_claim_reference = your_claim_reference;
    }









}
