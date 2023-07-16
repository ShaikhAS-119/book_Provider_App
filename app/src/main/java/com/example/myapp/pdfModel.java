package com.example.myapp;

public class pdfModel {

    private String url;
    private String name;
    private String category;

    public pdfModel(){}

    public pdfModel(String url, String name ) {
        this.url = url;
        this.name = name;
        this.category = category;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() { return category; }

    public void setCategory(String category) { this.category = category; }
}
