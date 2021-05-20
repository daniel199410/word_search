import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

public class DocxReader {
    private final String searchText;
    private FileWriter writer;

    public DocxReader(String searchText) {
        this.searchText = searchText;
    }

    public String getText(String path) {
        try(FileInputStream fis = new FileInputStream(path)) {
            XWPFDocument xDoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xDoc);
            return extractor.getText();
        } catch (Exception e) {
            return String.format("Error al leer el archivo %s", path);
        }
    }

    public void createFile(String path) {
        final File folder = new File(path);
        try {
            writer = new FileWriter(String.format("%s.txt", new Date()));
            getFiles(folder);
            writer.close();
        } catch (IOException e) {
            System.out.println("No se pudo crear el archivo");
        }
    }

    public void getFiles(File folder) {
        TextValidator textValidator = new TextValidator();
        File[] files = folder.listFiles();
        if(files != null) {
            for(final File file: files) {
                if(!file.isDirectory()) {
                    String text = this.getText(file.getAbsolutePath()).toLowerCase(Locale.ROOT);
                    try {
                        this.writer.write(String.format("%s\\%s: %s%n", file.getAbsolutePath(), file.getName(), textValidator.containsCharacters(text, searchText)));
                    } catch (IOException e) {
                        System.out.printf("El archivo %s\\%s no se pudo revisar", file.getAbsolutePath(), file.getName());
                    }
                    System.out.printf("%s\\%s: %s%n", file.getAbsolutePath(), file.getName(), textValidator.containsCharacters(text, searchText));
                } else {
                    getFiles(file);
                }
            }
        }
    }
}
