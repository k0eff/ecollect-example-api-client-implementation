package org.ecollect.api.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.ecollect.api.classes.*;

import java.io.IOException;
import java.util.ArrayList;

public class FileDataDeserializer extends StdDeserializer {

    public FileDataDeserializer() {
        this(null);
    }


    public FileDataDeserializer(Class<?> vc) {
        super(vc);
    }



    @Override
    public ArrayList<FileData> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        if (node == null) return null;

        ArrayList<FileData> list = new ArrayList<>(1);

        for (JsonNode fileDataItem : node) {

            String type = fileDataItem.get("type").asText();
            JsonParser fileDataItemParser = fileDataItem.traverse();
            fileDataItemParser.setCodec(p.getCodec());


            if (type.equals(ReceivableTypeEnum.CHARGE.toString().toLowerCase())) {
                try {
                    FileData fileData = new FileData();
                    fileData.setType(ReceivableTypeEnum.CHARGE);
                    AdditionalCharge charge = fileDataItemParser.readValueAs(AdditionalCharge.class);
                    fileData.setCharge(charge);
                    list.add(fileData);
                } catch (IOException e) {
                    throw e;
                }
            } else if (type.equals(ReceivableTypeEnum.CREDIT.toString().toLowerCase())) {
                try {
                    FileData fileData = new FileData();
                    fileData.setType(ReceivableTypeEnum.CREDIT);
                    Credit credit = (Credit) fileDataItemParser.readValueAs(Credit.class);
                    fileData.setCredit(credit);
                    list.add(fileData);
                } catch (IOException e) {
                    throw e;
                }
            } else if (type.equals(ReceivableTypeEnum.CLAIM.toString().toLowerCase())) {
                try {
                    FileData fileData = new FileData();
                    fileData.setType(ReceivableTypeEnum.CLAIM);
                    Claim claim = (Claim) fileDataItemParser.readValueAs(Claim.class);
                    fileData.setClaim(claim);
                    list.add(fileData);
                } catch (IOException e) {
                    throw e;
                }
            } else {
                throw new IOException("FileDataDeserializer Error: Unrecognized file data item.");
            }



//             TODO: Enable payment
//            else if (type.equals(FileDataTypeEnum.PAYMENT.toString().toLowerCase())")) {
//                FileData fileData = new FileData();
//                fileData.setType(FileDataTypeEnum.PAYMENT);
//                Payment payment = (Payment) fileDataItemParser.readValueAs(Payment.class);
//                fileData.setPayment(payment);
//                list.add(fileData);
//            }




        }

        if (!list.isEmpty()) return list;
        else return null;


    }


}




