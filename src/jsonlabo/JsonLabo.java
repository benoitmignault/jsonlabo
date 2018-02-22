package jsonlabo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.*;
import java.io.IOException;
import java.io.PrintWriter;
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

        System.out.println(newJson);

        /*        
        Cette fois, créez une commande en y ajoutant un item de chaque article dont le prix est inférieur à 28.00$. Ajuster le total en fonction du prix de chaque article
         */
        JSONObject exercice5 = new JSONObject();
        exercice5.put("noCommande", 0001);
        exercice5.put("date", "2018-02-15");
        
        float prixTotal = 0f;
        JSONArray articleInferieur = new JSONArray();

        for (int i = 0; i < fichierJson.length(); i++) {
            // object en preparation de comparaison pour les besoins de la cause
            JSONObject unArticle = fichierJson.getJSONObject(i);

            if (unArticle.getFloat("prix") < 28.00) {
                prixTotal += unArticle.getFloat("prix");
                articleInferieur.put(unArticle);
            }
        }

        exercice5.put("total", prixTotal);
        exercice5.put("articles", articleInferieur);

        try { // la variable true permet d'ajouter des informations
            FileWriter fw = new FileWriter("src/jsonlabo/new.json"/*,true*/);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);  
            pw.print(exercice5);
           
            pw.close(); bw.close(); fw.close();
        } catch (IOException ex) {
            System.out.println("Problème avec l'écriture dans le fichier");
        }

    }

    public static String StringFromFile(String PathToFile) {
        String fileContent = "";
        
        try {
            fileContent = new String(Files.readAllBytes(Paths.get(PathToFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        try {
            BufferedReader br = new BufferedReader(new FileReader(PathToFile));
            String line = br.readLine();
            while (line != null) {
                //System.out.println(line);    
                fileContent += line;
                line = br.readLine();
            }
            br.close();
        } catch (IOException ex) {
            System.out.println("Problème d'ouverture du fichier" + "biblio.txt");
        }
*/
        return fileContent;

    }

}
