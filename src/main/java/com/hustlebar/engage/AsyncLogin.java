package com.hustlebar.engage;

public class AsyncLogin implements Runnable {
    private EngageClient engageClient;
    public AsyncLogin(EngageClient engageClient) {
        this.engageClient = engageClient;
    }

    @Override
    public void run() {
        System.out.println("Async login thread started");
        engageClient.authenticate("tham@softwareag.com", "Tham#123");
    }
}
