package org.ecollect.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.ecollect.api.classes.FileData;
import org.ecollect.api.classes.ReceivableTypeEnum;

import java.io.IOException;
import java.util.ArrayList;

public class FileDataSerializer extends StdSerializer {

    public FileDataSerializer() {
        this(null);
    }

    public FileDataSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        ArrayList<FileData> fileDataItems = (ArrayList<FileData>) value;
        ArrayList<FileData> newArr = new ArrayList<>(1);

        try {
            gen.writeStartArray();
            for (FileData fileDataItem : fileDataItems) {
                if (fileDataItem.getType().equals(ReceivableTypeEnum.CHARGE)) gen.writeObject(fileDataItem.getCharge());
                else if (fileDataItem.getType().equals(ReceivableTypeEnum.CLAIM)) gen.writeObject(fileDataItem.getClaim());
                else if (fileDataItem.getType().equals(ReceivableTypeEnum.CREDIT)) gen.writeObject(fileDataItem.getCredit());
//              else if (fileDataItem.getType().equals(FileDataTypeEnum.PAYMENT)) gen.writeObject(fileDataItem.getPayment());
                // TODO: Enable payments
                else throw new IOException("FileDataSerializer Error: unknown FileData type");
            }
            gen.writeEndArray();
        } catch (IOException e) {
            throw e;
        }
    }
}


