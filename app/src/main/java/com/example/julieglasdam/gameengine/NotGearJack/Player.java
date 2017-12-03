package com.example.julieglasdam.gameengine.NotGearJack;

import android.graphics.Bitmap;

/**
 * Created by julieglasdam on 12/11/2017.
 */

public class Player {
    private final int WIDTH = 48;
    private final int HEIGHT = 65;
    private final int numberOfSprites = 10;
    private final int x = 50;
    private int y;
    private int animFrame;


    public Player(Bitmap bitmap, int y){
        this.y = y;
        this.animFrame = 0;

    }

    public void setAnimFrame() {

    }

    public int getAnimFrame() { return this.animFrame; }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWIDTH(){
        return this.WIDTH;
    }

    public int getHEIGHT() { return this.HEIGHT; }



    
}
