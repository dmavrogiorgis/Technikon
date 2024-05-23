package apiCalls.property;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeletePropertyByPropertyIdAndByOwnerId {

    static String propertyOwnerId = "16";
    static String propertyId = "2";

    public static void main(String[] args) {

        try {

            String url = "http://localhost:8080/api/owner/" + propertyOwnerId + "/property/" + propertyId;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Response code: " + response.statusCode());
            if (response.statusCode() == 200) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m - Property successfully deleted for the owner");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - Property deletion failed for the owner");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
