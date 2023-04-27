package lt.viko.eif.nlavkart.internetShopClient;

import org.apache.fop.apps.*;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Printer {
    public static void printHtml() throws IOException {
        String content = Files.readString(Path.of("src/main/resources/print.xml"));
        content = content.replaceAll("<\\?xml.*?>", "");
        content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"toHTML.xsl\"?>\n" + content;
        Files.writeString(Path.of("src/main/resources/print.xml"), content);
    }

    public static void printPdf() throws IOException, FOPException, TransformerException {
        String content = Files.readString(Path.of("src/main/resources/print.xml"));
        content = content.replaceAll("<\\?xml.*?>", "");
        content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + content;
        Files.writeString(Path.of("src/main/resources/print.xml"), content);

        File xsltFile = new File("src/main/resources/toPDF.xsl");
        StreamSource xmlSource = new StreamSource(new File("src/main/resources/print.xml"));
        FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
        FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
        OutputStream out;
        out = new java.io.FileOutputStream("print.pdf");
        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
            Result res = new SAXResult(fop.getDefaultHandler());
            transformer.transform(xmlSource, res);
        } finally {
            out.close();
        }
    }
}
