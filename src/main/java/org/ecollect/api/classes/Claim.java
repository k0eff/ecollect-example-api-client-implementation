package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.abstractClasses.AReceivable;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.interfaces.IReceivable;
import org.ecollect.api.serializers.LocalDateTimeSerializer;
import org.ecollect.api.utils.IdDenormalizer;

import java.time.LocalDateTime;

@JsonIgnoreProperties({"acting_as", "additional_charge_type"})
public class Claim extends AReceivable implements IReceivable {

    @JsonIgnore
    private final String type = "claim";


    private String id;  // property is denormalized

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonProperty(required = true)
    private LocalDateTime due_date;

    @JsonProperty(required = true)
    private String subject_matter;


    @JsonProperty(required = true)
    private ContractualItemEnum contractual_item;




    @JsonCreator
    public Claim() throws Exception {
        this.idDenormalizer = IdDenormalizer.getInstance();
    }




    private String normalizeClaimId(String oldId) {
        return oldId != null ? this.idDenormalizer.claim(oldId) : null;
    }


    public String getId() {
        return this.normalizeClaimId(id);
    }

    public void setId(String id) {
        this.id = this.normalizeClaimId(id);
    }

    public LocalDateTime getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDateTime due_date) {
        this.due_date = due_date;
    }


    public String getSubject_matter() {
        return subject_matter;
    }

    public void setSubject_matter(String subject_matter) {
        this.subject_matter = subject_matter;
    }



    @JsonGetter("contractual_item")
    public String getTypeJson() {
        return (contractual_item == null || contractual_item.equals("")) ? null : contractual_item.toString().toLowerCase();
    }

    public ContractualItemEnum getContractual_item() {
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

    public void setContractual_item(ContractualItemEnum contractual_item) {
        this.contractual_item = contractual_item;
    }



    @Override
    public String getType() {
        return type;
    }
}
