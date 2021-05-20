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
        DocxReader docxReader = new DocxReader(searchText);
        docxReader.createFile(path);
    }
}
