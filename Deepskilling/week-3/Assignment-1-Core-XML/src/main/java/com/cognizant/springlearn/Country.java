package com.cognizant.springlearn;

public class Country {

    private String code;
    private String name;

    public Country() {
        System.out.println("Inside Country Constructor."); // [cite: 99]
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        System.out.println("Inside setCode: " + code);
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("Inside setName: " + name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country [code=" + code + ", name=" + name + "]"; // [cite: 101]
    }
}
