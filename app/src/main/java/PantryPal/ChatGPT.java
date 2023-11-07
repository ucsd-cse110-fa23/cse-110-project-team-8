package PantryPal;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ChatGPT {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    // Jonathan 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
    // Henry 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
    private static final String MODEL = "text-davinci-003";

    public String chefGPT(String userInsructions) throws IOException, InterruptedException, URISyntaxException {

        int maxTokens = 100;

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", userInsructions);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

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
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        JSONObject responseJson = new JSONObject(responseBody);

        JSONArray choices = responseJson.getJSONArray("choices");
        String generatedRecipe = choices.getJSONObject(0).getString("text");

        return generatedRecipe;
    }

}
