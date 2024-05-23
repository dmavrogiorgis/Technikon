package apiCalls.propertyOwner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class PutPropertyOwnerByTin {

    static String tinNumber = "123456789";

    public static void main(String[] args) {

        String url = "http://localhost:8080/api/owner/update/" + tinNumber;
        String json = """
        {
          "address": "Nea Tyxaia Dieuthynsi 55",
          "email": "neo.tyxaio.email@example.com",
          "password": "NEWasdf1234!@"
        }
        """;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(json))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Test Passed - Property Owner Successfully Updated (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            } else if (response.statusCode() == 404) {
                System.out.println("Test failed - Property Owner not found (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("Test Failed (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
