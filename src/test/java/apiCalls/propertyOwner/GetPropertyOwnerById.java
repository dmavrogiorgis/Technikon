package apiCalls.propertyOwner;
import org.junit.Assert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static stepDefinitions.TestSetup.driver;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetPropertyOwnerById {

    public static void main(String[] args) {
        try {
            String uri = "http://localhost:8080/api/owner/search?tin=123456789";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            System.out.println("Response body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

