package com.hustlebar.portal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class PortalClient {
    private static final String Portal_Base = "https://api.webmethodscloud.com/abs/apirepository";
    private static final String tenant = "tham";

    public void getApis() {
        byte[] auth = Base64.getEncoder().encode("tham:xxx".getBytes(StandardCharsets.UTF_8));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(getApisPath()))
                .header("Authorization", "Basic " + new String(auth))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpClient client = HttpClient.newBuilder()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getApisPath() {
        return new StringBuilder(Portal_Base)
                .append("/")
                .append("apis")
                .append("?tenantId=" + tenant)
                .toString();
    }
}
