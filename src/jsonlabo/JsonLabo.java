package jsonlabo;

import org.json.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLabo {

    public static void main(String[] args) {
        String fileJson = StringFromFile("src/jsonlabo/labo.json");
        // afficher le fichier comme un string simple
        //System.out.println(fileJson); 
        
        JSONArray article = new JSONArray(fileJson);
        
        
        // exercice 1
        System.out.println(article.length());
        
        // exercice 2 affiche info des type film
        System.out.println("Nom des articles de type film :");
        for (int i = 0; i < article.length(); i++){
            // afficher les objets
            JSONObject unArticle = article.getJSONObject(i);
            
            // equal fait comparer la valeur
            if (unArticle.get("type").equals("film")){
                System.out.println(unArticle.get("nom"));
            }
        }
        
        // exercice 3 afficher les prix des articles 
        // que les quantites sont plus grand que 0
        
        for (int i = 0; i < article.length(); i++){
            // afficher les objets
            JSONObject unArticle = article.getJSONObject(i);
            
            // equal fait comparer la valeur
            if (unArticle.getInt("quantite") > 0){
                System.out.println(unArticle.getString("nom")+" "+unArticle.getFloat("prix"));
            }
        }        
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
        JSONObject obj = new JSONObject();
        JSONObject sousObj1 = new JSONObject();
        JSONObject sousObj2 = new JSONObject();
        sousObj1.put("id",125);
        sousObj2.put("nom","Breaking Bad");       
        obj.put("noCommande", 10432);
        obj.put("date", "2014-01-23");
        obj.put("total", 35.99);
        obj.accumulate("articles", sousObj1);
        obj.accumulate("articles", sousObj2);
        
        System.out.print(obj); 
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
