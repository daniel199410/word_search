import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.IOException;

public class DocxReader {
    public String getText(String path) {
        try(FileInputStream fis = new FileInputStream(path)) {
            XWPFDocument xDoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xDoc);
            return extractor.getText();
        } catch (IOException | InvalidFormatException e) {
            return "";
        }
    }


}
