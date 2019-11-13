package com.hustlebar.engage;

import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.util.concurrent.Flow;

public class EngageBodyPublisher implements HttpRequest.BodyPublisher {
    @Override
    public long contentLength() {
        return 0;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super ByteBuffer> subscriber) {

    }
}
