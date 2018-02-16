package jsonlabo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLabo {

    public static void main(String[] args) {
        String fileJson = StringFromFile("src/jsonlabo/labo.json");
        System.out.println(fileJson);
    }
    
    public static String StringFromFile(String PathToFile){
        String fileContent = "";
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
    
    
}
