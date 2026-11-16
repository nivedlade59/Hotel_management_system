/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package hotelmanagement.api;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class ApiClient {

    private static final String BASE_URL =
            "http://localhost:8080";

    private static final HttpClient CLIENT =
            HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

    private static final Gson GSON = new Gson();

    private ApiClient() {
        // Utility class: do not create objects of this class.
    }

    public static String get(String path)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(Duration.ofSeconds(20))
                .GET()
                .build();

        return send(request);
    }

    public static String post(String path, Object body)
            throws IOException, InterruptedException {

        String json = GSON.toJson(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        return send(request);
    }

    public static String patch(String path, Object body)
            throws IOException, InterruptedException {

        String json = GSON.toJson(body);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(Duration.ofSeconds(20))
                .header("Content-Type", "application/json")
                .method(
                        "PATCH",
                        HttpRequest.BodyPublishers.ofString(json)
                )
                .build();

        return send(request);
    }

    public static String delete(String path)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .timeout(Duration.ofSeconds(20))
                .DELETE()
                .build();

        return send(request);
    }

    private static String send(HttpRequest request)
            throws IOException, InterruptedException {

        HttpResponse<String> response =
                CLIENT.send(
                        request,
                        HttpResponse.BodyHandlers.ofString()
                );

        int status = response.statusCode();

        if (status < 200 || status >= 300) {
            throw new IOException(
                    "API request failed. HTTP status: "
                            + status
                            + ", response: "
                            + response.body()
            );
        }

        return response.body();
    }
}