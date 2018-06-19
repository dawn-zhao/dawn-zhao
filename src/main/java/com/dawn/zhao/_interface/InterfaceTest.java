package com.dawn.zhao._interface;

public interface InterfaceTest {

    public void testa();

    default String testb(){
        return "testB";
    }
}
