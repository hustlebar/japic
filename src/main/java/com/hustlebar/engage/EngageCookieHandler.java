package com.hustlebar.engage;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class EngageCookieHandler extends CookieHandler {
    @Override
    public Map<String, List<String>> get(URI uri, Map<String, List<String>> requestHeaders) throws IOException {
        return null;
    }

    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders) throws IOException {

    }
}
