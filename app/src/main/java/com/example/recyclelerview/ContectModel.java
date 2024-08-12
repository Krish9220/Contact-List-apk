package com.example.recyclelerview;

public class ContectModel {

    int image;
    String name,number;

    public ContectModel(int image, String name, String number) {
        this.image = image;
        this.name = name;
        this.number = number;
    }

    public ContectModel(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
