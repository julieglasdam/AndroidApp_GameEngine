package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;

/**
 * Created by julieglasdam on 13/11/2017.
 */

public class Ledge
{
    private Bitmap bitmap = null;
    private int x;
    private int y;
    private int width;
    private int height;

    public Ledge(Bitmap bitmap, int x, int y, int width, int height)
    {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    // Create the scrolling effect
    public void setX() {
        this.x-=4;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // Move the blocks to reuse
    public void moveLedge(int number) {
        this.x = this.x + number;
    }
}
