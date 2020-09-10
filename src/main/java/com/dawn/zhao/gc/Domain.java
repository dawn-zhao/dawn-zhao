package com.dawn.zhao.gc;

public class Domain {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() {
        System.out.println("Domain finalize");
    }
}
