package apiCalls.java.property;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPropertiesByOwnerId {

    public static void main(String[] args) {

        String propertyOwnerId = "21";

        try {
            String uri = "http://localhost:8080/api/owner/" + propertyOwnerId + "/property";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 302) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Status code: Expected: 302 Redirected - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Status code: Expected: 302 - Actual: " + response.statusCode() + ")");
            }

            if (response.body().contains(propertyOwnerId)) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Response body contains TIN number)");
                System.out.println("Response body:");
                printJson(response.body());
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Response body does not contain TIN number)");
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
