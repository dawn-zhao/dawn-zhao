package com.dawn.zhao.utils;

import java.io.*;

public class LocalEntityLineWriter implements EntityLineWriter {

    protected BufferedWriter writer;

    public LocalEntityLineWriter(String dataDir) throws IOException {
        File file = new File(dataDir).getParentFile();
        if(!file.exists() && file.mkdirs()){
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataDir, true)));
    }

    public LocalEntityLineWriter(String dataDir, boolean absoluted) throws IOException {
        File file = new File(dataDir).getParentFile();
        if(!file.exists() && file.mkdirs()){
        }
        writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dataDir, true)));
    }

    public void flush() throws IOException {
        writer.flush();
    }

    @Override
    public void writeLine(String entityLine) throws IOException {
        synchronized (this){
            writer.write(entityLine);
            writer.newLine();
        }
    }

    @Override
    public void close() throws IOException {
        writer.flush();
        writer.close();
    }

}
