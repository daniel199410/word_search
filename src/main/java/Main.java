import java.io.File;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static String searchText = "";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Escriba la ruta de la carpeta");
        String path = in.nextLine();
        System.out.println("Escriba la frase a buscar:");
        searchText = in.nextLine().toLowerCase(Locale.ROOT);
        in.close();
        final File folder = new File(path);
        getFiles(folder);
    }

    public static void getFiles(File folder) {
        DocxReader docxReader = new DocxReader();
        TextValidator textValidator = new TextValidator();
        File[] files = folder.listFiles();
        if(files != null) {
            for(final File file: files) {
                if(!file.isDirectory()) {
                    String text = docxReader.getText(file.getAbsolutePath()).toLowerCase(Locale.ROOT);
                    System.out.printf("%s\\%s: %s%n", file.getAbsolutePath(), file.getName(), textValidator.containsCharacters(text, searchText));
                } else {
                    getFiles(file);
                }
            }
        }
    }
}
