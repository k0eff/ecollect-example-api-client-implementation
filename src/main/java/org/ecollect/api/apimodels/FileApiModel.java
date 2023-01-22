package org.ecollect.api.apimodels;

        import org.ecollect.api.classes.File;
        import org.ecollect.api.classes.FileList;
        import org.ecollect.api.exceptions.EcollectAPIException;
        import org.ecollect.api.utils.Connection;
        import org.ecollect.api.utils.EcollectObjectMapper;

        import java.io.IOException;

public class FileApiModel {


    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "files";

    public FileApiModel(Connection conn, EcollectObjectMapper objectMapper) {
        this.connection = conn;
        this.objectMapper = objectMapper;
    }






    public FileList getManyFiles(int size, int from) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + connection.getLimitOffsetUriString(size,from);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, FileList.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        }
    }

    public File getFileByObj(File file) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateFileUri(file);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, File.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public File getFileById(String fileId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateFileUri(fileId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, File.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }




    public String generateFileUri(File file) {
        return file.getId() + "/";
    }

    public String generateFileUri(String fileId) {
        return fileId + "/";
    }














}
