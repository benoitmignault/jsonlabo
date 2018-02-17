package jsonlabo;

import java.io.BufferedReader;
import java.io.FileReader;
import org.json.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLabo {

    public static void main(String[] args) {
        String fileJson = StringFromFile("src/jsonlabo/labo.json");
        // afficher le fichier comme un string simple
        // exercice 0
        System.out.println(fileJson);

        JSONArray fichierJson = new JSONArray(fileJson);
        // exercice 1
        System.out.println("Voici la longueur du array : " + fichierJson.length() + "\n");

        // exercice 2 affiche info des type film
        System.out.println("Nom des articles de type film :");
        for (int i = 0; i < fichierJson.length(); i++) {
            // afficher les objets
            JSONObject unArticle = fichierJson.getJSONObject(i);

            // equal fait comparer la valeur
            if (unArticle.get("type").equals("film")) {
                System.out.println(unArticle.get("nom"));
            }
        }
        System.out.println();

        // exercice 3 afficher les prix des articles 
        // que les quantites sont plus grand que 0
        for (int i = 0; i < fichierJson.length(); i++) {
            // afficher les objets
            JSONObject unArticle = fichierJson.getJSONObject(i);

            // equal fait comparer la valeur
            if (unArticle.getInt("quantite") > 0) {
                System.out.println(unArticle.getString("nom") + " : " + unArticle.getFloat("prix"));
            }
        }
        System.out.println();
        // exercice 4 creer un objet
        /*
        { accolade est un objet
            "noCommande": 10432,
            "date": "2014-01-23",
            "total": 35.99,
            "articles": [ crochet est un array
                            {
                                "id": 125,
                                "nom": "Breaking Bad"
                            }
                        ]
        }
         */
        JSONObject newJson = new JSONObject();
        JSONObject articles1 = new JSONObject();
        JSONObject articles2 = new JSONObject();
        articles1.put("id", 125);
        articles2.put("nom", "Breaking Bad");
        newJson.put("noCommande", 10432);
        newJson.put("date", "2014-01-23");
        newJson.put("total", 35.99);
        newJson.put("articles", articles1);
        // obj.put("articles", articles2); cette action écrase la précédente comme il a le même nom
        newJson.accumulate("articles", articles2);

        System.out.print(newJson);

    }

    public static String StringFromFile(String PathToFile) {
        String fileContent = "";
        /*
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            BufferedReader br = new BufferedReader(new FileReader(PathToFile));
            String line = br.readLine();
            while (line != null) {
                //System.out.println(line);    
                fileContent += line;
                line = br.readLine();
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("Problème d'ouverture du fichier" + "biblio.txt");
        }

        return fileContent;

    }

}
