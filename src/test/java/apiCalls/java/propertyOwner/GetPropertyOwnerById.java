package apiCalls.java.propertyOwner;

import apiCalls.java.support.PrettyJsonPrinter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPropertyOwnerById {

    static String tinNumber = "123456789";

    public static void main(String[] args) {

        try {
            String uri = "http://localhost:8080/api/owner/search?tin=" + tinNumber;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Status code: Expected: 200 - Actual: " + response.statusCode() + ")");
            }

            if (response.body().contains(tinNumber)) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m (Response body contains TIN number)");
                System.out.println("Response body:");
                PrettyJsonPrinter.printJson(response.body());
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m (Response body does not contain TIN number)");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCodeGetPropertyOwnerById() {
        try {
            String uri = "http://localhost:8080/api/owner/search?tin=" + tinNumber;
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
