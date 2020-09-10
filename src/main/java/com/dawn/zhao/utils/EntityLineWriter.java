package com.dawn.zhao.utils;

import java.io.Closeable;
import java.io.IOException;

public interface EntityLineWriter extends Closeable {
    
    void writeLine(String entityLine) throws IOException;

}
