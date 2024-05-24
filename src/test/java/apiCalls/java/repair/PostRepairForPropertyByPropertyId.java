package apiCalls.java.repair;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class PostRepairForPropertyByPropertyId {

    public static void main(String[] args) {

        try {
            String propertyId = "3";
            String url = "http://localhost:8080/api/owner/property/" + propertyId + "/repair";

            String requestBody = """
                    {
                      "typeOfRepair": "PAINTING",
                      "description": "Paint the living room and bathroom.",
                      "repairDate": "2024-05-24",
                      "propertyId": 3
                    }
                    """;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m - Property successfully created (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else if (response.statusCode() == 409) {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - \u001B[33mProperty already exists in the database\u001B[0m (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - Property creation failed (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            }

            if (!response.body().isEmpty()) {
                if (response.body().contains("201")) {
                    System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Response body: not empty)");
                    System.out.println("Response body:");
                    printJson(response.body());
                } else if (response.body().contains("400")) {
                    System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Response body: contains error 400 - Bad Request)");
                    System.out.println("Response body:");
                    printJson(response.body());
                }
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Response body: empty)");
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
