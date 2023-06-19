package com.example.diseaseprediction.helpers;

public class Data {
    private String text;
    String news;
    String newsDesc;

    public Data(String news, String newsDesc) {
        this.news = news;
        this.newsDesc = newsDesc;
    }

    public String getText() {
        text = news + ":" + newsDesc;
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}