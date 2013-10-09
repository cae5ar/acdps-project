package com.pstu.acdps.client.components;

public enum ENavButtons {

    HOME_PAGE("Домой", "/");
    public String text;
    public String link;

    private ENavButtons(String text, String link) {
        this.text = text;
        this.link = link;
    }
}