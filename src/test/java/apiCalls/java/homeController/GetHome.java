package apiCalls.java.homeController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetHome {

    public static void main(String[] args) {

        try {

            String uri = "http://localhost:8080/home";
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

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCodeGetHome() {
        try {
            String uri = "http://localhost:8080/home";
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
