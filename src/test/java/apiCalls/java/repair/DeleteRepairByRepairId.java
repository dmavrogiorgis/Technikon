package apiCalls.java.repair;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteRepairByRepairId {

    public static void main(String[] args) {

        try {

            String repairId = "6";
            String url = "http://localhost:8080/api/owner/repair/" + repairId;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200 && response.body().equals("true")) {
                System.out.println("\u001B[1m\u001B[32mTest Passed\u001B[0m - Repair successfully deleted (Status code: 200)");
            } else if (response.statusCode() == 200 && response.body().equals("false")) {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - Repair deletion failed (Status code: 200, but response body: false)");
            } else {
                System.out.println("\u001B[1m\u001B[31mTest Failed\u001B[0m - Unexpected status code: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
