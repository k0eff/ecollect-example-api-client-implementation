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

public class DocumentGetSingleTest {

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
        File f = new File("src/test/fixtures/hello-world.pdf");
        doc.setFile(f);
        return doc;
    }

    public Document postDocument(DocumentPost doc) throws EcollectAPIException, IOException, MagicParseException, MagicException, MagicMatchNotFoundException {
        return api.document.postDocument(doc);
    }



    @Test
    public void DocumentGetSingleTest() throws Exception {
        DocumentPost postedDoc = createDocumentToPost();
        Document createdDoc = postDocument(postedDoc);

        Document docGotten = api.document.getDocumentByObj(createdDoc);
        Assert.assertTrue(docGotten.getFilename().equals(createdDoc.getFilename()));


    }

}