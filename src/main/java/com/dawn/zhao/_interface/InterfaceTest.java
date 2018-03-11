package com.dawn.zhao._interface;

public interface InterfaceTest {

    public void testa();

    public default String testb(){
        return "testB";
    }
}
