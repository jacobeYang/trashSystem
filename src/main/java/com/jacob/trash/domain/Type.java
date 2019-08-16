package com.jacob.trash.domain;

public class Type {

    private int value;
    private String name;
    public Type(){

    }

    public Type(int value,String name){
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
