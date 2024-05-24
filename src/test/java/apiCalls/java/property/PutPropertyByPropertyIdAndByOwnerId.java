package apiCalls.java.property;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;

public class PutPropertyByPropertyIdAndByOwnerId {

    public static void main(String[] args) {
        try {
            String ownerId = "22";
            String propertyId = "3";
            String requestBody = """
                    {
                       "id": 0,
                       "propertyIN": 123456789,
                       "address": "New Random Address 66",
                       "yearOfConstruct": 2009,
                       "propertyOwnerId": 22,
                       "picturePath": "mew two jpeg",
                       "typeOfProperty": "MAISONETTE"
                    }
                    """;

            String url = "http://localhost:8080/api/owner/" + ownerId + "/property/" + propertyId;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("Content-Type", "application/json").PUT(BodyPublishers.ofString(requestBody)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("\\u001B[1m\\u001B[32mTest Passed\\u001B[0m - Property Owner Successfully Updated (Status code: Expected: 200 - Actual: \" + response.statusCode() + \")");
            } else if (response.statusCode() == 404) {
                System.out.println("\\u001B[1m\\u001B[31mTest Failed\\u001B[0m - \\u001B[33mProperty Owner not found\\u001B[0m (Status code: Expected: 200 - Actual: \" + response.statusCode() + \")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
