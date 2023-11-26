package com.example.projectgitm8;

public class BackgroundColorChangedEvent {
    private final int newColor;

    public BackgroundColorChangedEvent(int newColor) {
        this.newColor = newColor;
    }

    public int getNewColor() {
        return newColor;
    }
}