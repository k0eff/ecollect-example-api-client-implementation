package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.*;
import org.ecollect.api.abstractClasses.AConciliation;
import org.ecollect.api.interfaces.IConciliation;
import org.ecollect.api.utils.IdDenormalizer;


public class Credit extends AConciliation implements IConciliation {

    @JsonIgnore
    private final String type = "credit";


    private String id;  // property is denormalized


    @JsonCreator
    public Credit() throws Exception {
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    @Override
    public String getType() {
        return type;
    }



}
