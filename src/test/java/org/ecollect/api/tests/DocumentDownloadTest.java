package org.ecollect.api.tests;

import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;
import org.ecollect.api.EcollectAPIHandler;
import org.ecollect.api.classes.Customer;
import org.ecollect.api.classes.Document;
import org.ecollect.api.classes.DocumentPost;
import org.ecollect.api.exceptions.EcollectAPIException;
import org.ecollect.api.tests.helpers.ApiHandlerHelper;
import org.ecollect.api.tests.helpers.CustomerTestHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DocumentDownloadTest {

    private final String pathUploadedFile = "src/test/fixtures/hello-world.pdf";
    private final String pathDownloadedFile = "src/test/fixtures/hello-world-downloaded.pdf";

    private EcollectAPIHandler api;
    @Before
    public void setup() {
        api = ApiHandlerHelper.setupApi();
    }



    public DocumentPost createDocumentToPost() throws Exception {
        DocumentPost doc = new DocumentPost();

        Customer custRef = CustomerTestHelper.createCustomerOrg(api);
        String custRefId = custRef.getId();

        doc.setCustomer(custRefId);
        File f = new File(pathUploadedFile);
        doc.setFile(f);
        return doc;
    }

    public Document postDocument(DocumentPost doc) throws EcollectAPIException, IOException, MagicParseException, MagicException, MagicMatchNotFoundException {
        return api.document.postDocument(doc);
    }



    @Test
    public void DocumentDownloadTest() throws Exception {
        DocumentPost postedDoc = createDocumentToPost();
        Document createdDoc = postDocument(postedDoc);

        createdDoc.setLink("http://localhost:3320/documents/5b1161e8-39fc-415b-844f-dcc8d858472f/download"); // for internal Ecollect testing only

        InputStream streamDownloaded = api.document.downloadDocument(createdDoc);
        Files.copy(streamDownloaded, Paths.get(pathDownloadedFile), StandardCopyOption.REPLACE_EXISTING);
        streamDownloaded.close();
        File fileDownloaded = new File(pathDownloadedFile);

        Assert.assertTrue(postedDoc.getFile().length()==fileDownloaded.length());

    }

}