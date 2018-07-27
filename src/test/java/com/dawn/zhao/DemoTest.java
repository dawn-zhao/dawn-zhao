package com.dawn.zhao;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DemoTest {

    @Test
    public void getUnSafeFel() throws Throwable {

        Unsafe unsafe = getUnsafe();
        int base = unsafe.arrayBaseOffset(long[].class);
        System.out.println(base);
    }

    private Unsafe getUnsafe() throws Throwable {
        Class<?> unsafeClass = Unsafe.class;
        for (Field f : unsafeClass.getDeclaredFields()) {
            if ("theUnsafe".equals(f.getName())) {
                f.setAccessible(true);
                return (Unsafe) f.get(null);
            }
        }
        throw new IllegalAccessException("no declared field: theUnsafe");

    }
}
