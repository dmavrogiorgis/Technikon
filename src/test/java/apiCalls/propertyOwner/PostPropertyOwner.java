package apiCalls.propertyOwner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class PostPropertyOwner {

    public static void main(String[] args) {

        String url = "http://localhost:8080/api/owner";
        String json = """
        {
          "id": 0,
          "tin": "123456789",
          "name": "Tyxaios",
          "surname": "Tyxaiopoulos",
          "address": "Tyxaia Dieuthynsi 55",
          "phoneNumber": "6988552255",
          "email": "tyxaio.email@example.com",
          "username": "rnd(uname5)",
          "password": "asdf1234!@",
          "active": true
        }
        """;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("Test Passed - Property Owner successfully created (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else if (response.statusCode() == 409) {
                System.out.println("Test Failed - Property Owner already exists in the database (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("Test Failed - Property Owner creation failed (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            }

            if (!response.body().isEmpty()) {
                System.out.println("Test Passed (Response body: not empty)");
                System.out.println("Response body:");
                printJson(response.body());
            } else {
                System.out.println("Test Failed (Response body: empty)");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printJson(String pjson) {
        pjson = pjson.trim().substring(1, pjson.length() - 1);
        String[] keyValuePairs = pjson.split(",");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            System.out.println(key + ": " + value);
        }
    }
}
