package apiCalls.java.propertyOwner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import static apiCalls.java.support.PrettyJsonPrinter.printJson;

public class PostPropertyOwner2 {

    public static void main(String[] args) {

        try {

            String url = "http://localhost:8080/api/owner";
            String json = """
                    {
                      "id": 0,
                      "tin": "223456789",
                      "name": "Aristidis",
                      "surname": "Aristidou",
                      "address": "Tyxaia Dieuthynsi 52",
                      "phoneNumber": "6988552252",
                      "email": "tyxaio.email.2@example.com",
                      "username": "rnd(uname2)",
                      "password": "qwert12345%",
                      "active": true
                    }
                    """;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(json))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 201) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m - Property Owner successfully created (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else if (response.statusCode() == 409) {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - \u001B[33mProperty Owner already exists in the database\u001B[0m (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - Property Owner creation failed (Status code: Expected: 201 - Actual: " + response.statusCode() + ")");
            }

            if (!response.body().isEmpty()) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Response body: not empty)");
                System.out.println("Response body:");
                printJson(response.body());
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Response body: empty)");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCodePostPropertyOwner2() {

        try {

            String uri = "http://localhost:8080/api/owner";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return 0;

    }
}
