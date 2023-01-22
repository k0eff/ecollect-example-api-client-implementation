package org.ecollect.api.apimodels;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.ecollect.api.classes.Document;
import org.ecollect.api.classes.DocumentPost;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.utils.Connection;
import org.ecollect.api.utils.EcollectObjectMapper;
import net.sf.jmimemagic.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DocumentApiModel {


    private Connection connection;
    private EcollectObjectMapper objectMapper;

    private final String baseUri = "documents";



    public DocumentApiModel(Connection connection, EcollectObjectMapper objectMapper) {
        this.connection = connection;
        this.objectMapper = objectMapper;
    }


    public Document postDocument(DocumentPost docPost) throws EcollectAPIException, IOException, MagicMatchNotFoundException, MagicParseException, MagicException {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            MagicMatch match = Magic.getMagicMatch(docPost.getFile(), false);
            String mimetype = match.getMimeType();
            ContentType contentType = ContentType.create(match.getMimeType());
            builder.addBinaryBody("file", docPost.getFile(), contentType, docPost.getFile().getName());

            // allow only 1 id to be passed
            if (docPost.getCustomer() != null) builder.addTextBody("customer", docPost.getCustomer(), ContentType.TEXT_PLAIN);
            else if (docPost.getClaim() != null) builder.addTextBody("claim", docPost.getClaim(), ContentType.TEXT_PLAIN);
            else if (docPost.getInvoice() != null) builder.addTextBody("invoice", docPost.getInvoice(), ContentType.TEXT_PLAIN);
            else if (docPost.getCharge() != null) builder.addTextBody("charge", docPost.getCharge(), ContentType.TEXT_PLAIN);

            HttpEntity entity = builder.build();
            String resp = connection.submitPostRequestMultipart(baseUri, entity);
            return objectMapper.readValue(resp, Document.class);
        } catch (EcollectAPIException | IOException e) {
            throw e;
        } catch (MagicException e) {
            throw e;
        } catch (MagicParseException e) {
            throw e;
        } catch (MagicMatchNotFoundException e) {
            throw e;
        }
    }


    public Document getDocumentByObj(Document document) throws EcollectAPIException, IOException {
        try {
            String uri = baseUri + "/" + this.generateDocumentUri(document);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Document.class);
        } catch (EcollectAPIException e) {
            throw e;
        }
    }

    public Document getDocumentById(String documentId) throws IOException, EcollectAPIException {
        try {
            String uri = baseUri + "/" + this.generateDocumentUri(documentId);
            String resp = connection.submitGetRequest(uri);
            return objectMapper.readValue(resp, Document.class);
        } catch (IOException | EcollectAPIException e) {
            throw e;
        }
    }


    public InputStream downloadDocument(Document document) throws IOException {
        try {
            String link = document.getLink();
            InputStream resp = connection.submitGetReqBinResponse(link);
            return resp;
        } catch (Exception e) {
            throw e;
        }
    }


    public String generateDocumentUri(Document document) {
        return document.getId() + "/";
    }

    public String generateDocumentUri(String documentId) {
        return documentId + "/";
    }






}