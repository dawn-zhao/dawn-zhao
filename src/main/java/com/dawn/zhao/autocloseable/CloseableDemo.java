package com.dawn.zhao.autocloseable;

import java.io.IOException;

/**
 * 自动关闭资源Demo
 */
public class CloseableDemo {

    public static void main(String[] args) {
        try (Resources resources = new Resources();){
            System.out.println("service execute");
        } catch (IOException e) {
            System.out.println("catch execute");
            e.printStackTrace();
        } finally {
            System.out.println("finally execute");
        }
    }
}
