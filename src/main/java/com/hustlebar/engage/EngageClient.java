package com.hustlebar.engage;

import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EngageClient {
    public static final String HOST = "https://engage.softwareagdev.cloud";

    public void authenticate(String email, String password) {
        User user = new User(email, password);
        String userJson = JsonbBuilder.create()
                .toJson(user);

        System.out.println(userJson);

        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HOST + "/uaa/api/v1/login"))
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            final HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
