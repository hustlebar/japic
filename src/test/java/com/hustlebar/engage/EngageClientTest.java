package com.hustlebar.engage;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EngageClientTest {
    @Test
    public void testAuthentication() {
        Path path = Paths.get("auth.json");
        System.out.println(path);
        new EngageClient().authenticate("tham@softwareag.com", "Tham#123", path);
    }
}
