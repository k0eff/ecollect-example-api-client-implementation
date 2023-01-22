package org.ecollect.api.classes;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.ecollect.api.deserializers.FileDataDeserializer;
import org.ecollect.api.deserializers.LocalDateTimeDeserializer;
import org.ecollect.api.serializers.FileDataSerializer;
import org.ecollect.api.serializers.LocalDateTimeSerializer;
import org.ecollect.api.utils.IdDenormalizer;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;




public class File {

    private String id; // property is denormalized
    private String customer; // property is denormalized
    private Balance balance;
    private ArrayList<Document> documents;


    @JsonSerialize(using = FileDataSerializer.class)
    @JsonDeserialize(using = FileDataDeserializer.class)
    private ArrayList<FileData> data;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime created_at;


    @JsonIgnore
    public IdDenormalizer idDenormalizer;








    @JsonCreator
    public File() throws Exception {
        this.idDenormalizer = IdDenormalizer.getInstance();
    }



    private String normalizeFileId(String oldId) {
        return oldId != null ? this.idDenormalizer.file(oldId) : null;
    }
    private String normalizeCustomerId(String oldId) {
        return oldId != null ? this.idDenormalizer.customer(oldId) : null;
    }




    public String getId() {
        return this.normalizeFileId(id);
    }

    public void setId(String file) {
        this.id = this.normalizeFileId(file);
    }

    public String getCustomer() {
        return this.normalizeCustomerId(customer);
    }

    public void setCustomer(String customer) {
        this.customer = this.normalizeCustomerId(customer);
    }


    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
}
