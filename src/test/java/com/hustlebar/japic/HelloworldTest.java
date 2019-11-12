package com.hustlebar.japic;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class HelloworldTest {
    @Test
    public void testHello() throws IOException, InterruptedException {
        new Helloworld().hello();
    }
}
