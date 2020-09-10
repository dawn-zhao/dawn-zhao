package com.dawn.zhao.utils;

import java.io.Closeable;
import java.io.IOException;

public class EntityAppender<E> implements Closeable {
    
    private final EntityLineWriter entityLineWriter;
    
    public EntityAppender(EntityLineWriter entityLineWriter) {
        this.entityLineWriter = entityLineWriter;
    }
    
    public void append(E entity) throws IOException {
        entityLineWriter.writeLine(entity.toString());
    }

    @Override
    public void close() throws IOException {
        entityLineWriter.close();
    }

}
