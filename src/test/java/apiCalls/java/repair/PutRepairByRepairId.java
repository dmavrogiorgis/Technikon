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
                      "propertyId": 6,
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
