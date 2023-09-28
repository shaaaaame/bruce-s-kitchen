import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.api-ninjas.com/v1/recipe?query=chicken%20alfredo"))
            .header("X-Api-Key", "zgSAUkuV/RadzPi5Zmg/YQ==3CRbkHrEG4ZKiyhQ")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }
}