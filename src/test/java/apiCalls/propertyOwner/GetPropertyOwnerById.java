package apiCalls.propertyOwner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPropertyOwnerById {

    public static void main(String[] args) {

        String tinNumber = "123456789";

        try {
            String uri = "http://localhost:8080/api/owner/search?tin=" + tinNumber;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Test Passed (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
                System.out.println("Response body:");
                printJson(response.body());
            } else {
                System.out.println("Test Failed (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            }

            if (response.body().contains(tinNumber)) {
                System.out.println("Test Passed (Response body contains TIN number)");
            } else {
                System.out.println("Test Failed (Response body does not contain TIN number)");
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
