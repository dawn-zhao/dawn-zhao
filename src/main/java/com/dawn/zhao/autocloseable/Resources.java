package com.dawn.zhao.autocloseable;

import java.io.Closeable;
import java.io.IOException;

public class Resources implements Closeable {

    public Resources() {
        System.out.println("resource got");
    }

    @Override
    public void close() throws IOException {
        System.out.println("resource done");
    }
}
