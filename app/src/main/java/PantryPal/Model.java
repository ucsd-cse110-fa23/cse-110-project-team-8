package PantryPal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

public class Model {
    private static final String GET_ALL = "GET_ALL";

    public String performRequest(String method, String recipeTitle, String ingredients, String instructions) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/";
            if (recipeTitle != null) {
                urlString += "?=" + recipeTitle;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("POST") || method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(recipeTitle + "," + ingredients + "," + instructions);
                out.flush();
                out.close();
            }
            // request for GET all recipes
            if (method.equals("GET") && recipeTitle.equals("") && ingredients.equals("") && instructions.equals("")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(GET_ALL);
                out.flush();
                out.close();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}
