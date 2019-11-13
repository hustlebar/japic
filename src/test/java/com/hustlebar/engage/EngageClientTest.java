package com.hustlebar.engage;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EngageClientTest {
    @Test
    public void testAuthentication() {
        new EngageClient().authenticate("tham@softwareag.com", "Tham#123");
    }

    @Test
    public void testGetOrganizations() {
        new EngageClient().getOrganizations();
    }
}
