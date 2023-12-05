package PantryPal;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;

import java.io.*;
import java.net.*;
import java.util.*;

public class Model {
    public static final int timeoutInMillis = 100000;

    public String performRequest(String method, String username, String password, String recipeTitle,
            String ingredients, String instructions, String creationTime, String action) { // action can only be either "createAccount" or
                                                                      // "Login"
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/" + username;
            if (recipeTitle != null) {
                urlString += "?=" + recipeTitle.replace(" ", "%20");

            } else {
                urlString += "?=" + "defaultLoad";
            }

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            System.out.println("requested method: " + method);

            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            conn.setConnectTimeout(timeoutInMillis);
            conn.setReadTimeout(timeoutInMillis);

            // System.out.println("actual method"+ conn.getRequestMethod());

            if ((method.equals("POST") || method.equals("PUT"))) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(username + ";" + password + ";" + recipeTitle + ";" + ingredients + ";" + instructions + ";" + creationTime
                    + ";" + action);
                out.flush();
                out.close();
            } else if (method.equals("DELETE")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(recipeTitle);
                out.flush();
                out.close();
            }
            String response = "";
            InputStream instream = conn.getInputStream();
            // Read the response data
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            response = reader.readLine();
            // Close the reader
            // Close the reader
            // Print the response data
            System.out.println("Response Data: " + response);

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}
