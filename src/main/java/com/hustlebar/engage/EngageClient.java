package com.hustlebar.engage;

import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @author tham
 */
public class EngageClient {
    public static final String HOST = "https://engage.softwareagdev.cloud";
    HttpClient client = null;

    public void authenticate(String email, String password) {
        System.out.println("Enters authenticate()");
        User user = new User(email, password);
        String userJson = JsonbBuilder.create()
                .toJson(user);

        System.out.println(userJson);

        CookieHandler.setDefault(new CookieManager());

        if (client == null) {
            client = HttpClient.newBuilder()
                    .cookieHandler(CookieHandler.getDefault())
                    .build();
        }

        try {
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(HOST + "/uaa/api/v1/login"))
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            final HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            System.out.println(response.body());
            List<String> cookies = response.headers().allValues("set-cookie");
            System.out.println("Size: " + cookies.size());
            for (String cookie: cookies) {
                System.out.println(">>> " + cookie);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        final HttpRequest secondReq = HttpRequest.newBuilder()
                .uri(URI.create(HOST + "/engine/api/v1/apis"))
                .header("content-type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(secondReq, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void getOrganizations() {
        CookieHandler cookieHandler = CookieHandler.getDefault();
        System.out.println(cookieHandler);
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(HOST + "/engine/api/v1/organizations"))
                .header("content-type", "application/json")
                .GET()
                .build();

        try {
            final HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    public void concurrentLogin(int count) {
        System.out.println("Number of threads are: " + count);
        for (int i = 0; i < count; i++) {
            new Thread(new AsyncLogin(this)).start();
        }
    }

    private void startThread() {
        final Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("Login initiated");
                authenticate("tham@softwareag.com", "Tham#123");
            }
        };

        new Thread(runnable).start();
    }
}
