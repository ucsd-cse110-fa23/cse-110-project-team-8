package VoiceInput;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.json.JSONArray;
import org.json.JSONObject;

//Borrowed from CSE 110 Lab 7 
//https://docs.google.com/document/d/1lZDUFcTV7NrHEHcYKqH1PnmpRSEjvcAH0Wc2qg6_ec8/edit#heading=h.z1qnwebb5puw 
public class DallE{

    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations";
    private static final String API_KEY = "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";
    // Jonathan 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
    // Henry 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
    // Gustavo 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
    private static final String MODEL = "dall-e-2";


    static public String chefDallE(String userInsructions, String recipeTitle) throws IOException, InterruptedException, URISyntaxException {

   //number of images you want to produce
   int n = 1;


   // Create a request body which you will pass into request object
   JSONObject requestBody = new JSONObject();
   requestBody.put("model", MODEL);
   requestBody.put("prompt", userInsructions);
   requestBody.put("n", n);
   requestBody.put("size", "256x256");


   // Create the HTTP client
   HttpClient client = HttpClient.newHttpClient();


   // Create the request object
   HttpRequest request = HttpRequest
     .newBuilder()
     .uri(URI.create(API_ENDPOINT))
     .header("Content-Type", "application/json")
     .header("Authorization", String.format("Bearer %s", API_KEY))
     .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
     .build();


   // Send the request and receive the response
   HttpResponse<String> response = client.send(
     request,
     HttpResponse.BodyHandlers.ofString()
   );


   // Process the response
   String responseBody = response.body();
   JSONObject responseJson = new JSONObject(responseBody);

   // Extract the URL from the JSON response
   JSONArray dataArray = responseJson.getJSONArray("data");
   String generatedImageURL = dataArray.getJSONObject(0).getString("url");
   
   System.out.println("DALL-E Response:");
   System.out.println(generatedImageURL);

   // Download the Generated Image to Current Directory
   try (InputStream in = new URI(generatedImageURL).toURL().openStream()) {
    //Files.copy(in, Paths.get("image.png"));
    Files.copy(in, Paths.get(recipeTitle + ".png"), StandardCopyOption.REPLACE_EXISTING);
   }

   return generatedImageURL;

   }

}