package com.dawn.zhao.utils;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntityAppenders<E> implements Closeable {

    private final EntityLineWriterBuilder entityLineWriterBuilder;
    private final Map<String, EntityAppender<E>> businessDateToAppender;
    
    public EntityAppenders(EntityLineWriterBuilder entityLineWriterBuilder) {
        this.entityLineWriterBuilder = entityLineWriterBuilder;
        this.businessDateToAppender = new HashMap<>();
    }

    public EntityAppender<E> appender(String businessDate) throws IOException {
        EntityAppender<E> appender = businessDateToAppender.get(businessDate);
        if (appender == null) {
            appender = new EntityAppender<>(entityLineWriterBuilder.build(businessDate));
            businessDateToAppender.put(businessDate, appender);
        }
        return appender;
    }

    @Override
    public void close() {
        for (EntityAppender<E> appender : businessDateToAppender.values()) {
            try {
                appender.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public static interface EntityLineWriterBuilder {
        
        EntityLineWriter build(String businessDate) throws IOException;
        
    }

}
