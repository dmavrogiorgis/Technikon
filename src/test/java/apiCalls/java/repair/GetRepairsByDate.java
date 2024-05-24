package apiCalls.java.repair;

import apiCalls.java.support.PrettyJsonPrinter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class GetRepairsByDate {

    public static void main(String[] args) {

        String date = LocalDate.now().toString();

        try {
            String uri = "http://localhost:8080/api/owner/repair/" + date;
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
            System.out.println("Response body:");
            PrettyJsonPrinter.printJson(response.body());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
