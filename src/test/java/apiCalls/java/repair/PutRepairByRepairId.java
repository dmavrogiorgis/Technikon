package apiCalls.java.repair;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class PutRepairByRepairId {

    public static void main(String[] args) {

        try {

            String repairId = "6";
            String requestBody = """
                    {
                      "typeOfRepair": "PAINTING",
                      "description": "Paint the kitchen and balcony.",
                      "repairDate": "2024-05-30",
                      "propertyId": 3,
                      "statusOfRepair": "PENDING",
                      "active": true
                    }
                    """;

            String url = "http://localhost:8080/api/owner/repair/" + repairId;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(BodyPublishers.ofString(requestBody))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m - Property successfully created (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - \u001B[33mProperty already exists in the database\u001B[0m (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
