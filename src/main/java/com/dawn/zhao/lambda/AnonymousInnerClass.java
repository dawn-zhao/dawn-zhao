package com.dawn.zhao.lambda;

import java.util.function.Consumer;

public class AnonymousInnerClass {
    static <T> void doWith(T obj, Consumer<T> consumer) {
        consumer.accept(obj);
    }

    public static void main(String[] args) {
        doWith(new Object() {
            void starPlatinum() {
                System.out.println("Star finger!");
            }
        }, obj -> obj.starPlatinum());
    }
}
