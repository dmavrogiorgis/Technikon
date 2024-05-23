package apiCalls.propertyOwner;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeletePropertyOwnerByTin {

    static String tinNumber = "123456789";

    public static void main(String[] args) {

        String url = "http://localhost:8080/api/owner/delete/"  + tinNumber;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            if (response.statusCode() == 200) {
                System.out.println("Test Passed - Property Owner successfully deleted");
            } else {
                System.out.println("Test Failed - Property Owner deletion failed");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
