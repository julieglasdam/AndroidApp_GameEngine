package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;

/**
 * Created by julieglasdam on 12/11/2017.
 */

public class Player {
    private final int WIDTH = 34;
    private final int HEIGHT = 24;
    private final int numberOfSprites = 10;
    private final int x = 50;
    private int y;
    private int animFrame;
    private int jumpHeight = 40;


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

    public void jump() {
        this.y -= jumpHeight;
    }

    public int getWIDTH(){
        return this.WIDTH;
    }

    public int getHEIGHT() { return this.HEIGHT; }



    
}
