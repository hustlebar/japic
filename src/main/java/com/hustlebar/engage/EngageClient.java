package com.hustlebar.engage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EngageClient {
    public static final String HOST = "https://engage.softwareagdev.cloud";

    public void authenticate(String email, String password, Path path) {
        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HOST + "/uaa/api/v1/login"))
                    .POST(HttpRequest.BodyPublishers.ofFile(path))
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
