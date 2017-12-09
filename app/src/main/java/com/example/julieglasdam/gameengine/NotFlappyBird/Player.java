package com.example.julieglasdam.gameengine.NotFlappyBird;

import android.graphics.Bitmap;

/**
 * Created by julieglasdam on 12/11/2017.
 */

public class Player
{
    private final int WIDTH = 39;
    private final int HEIGHT = 28;
    private final int numberOfSprites = 3;
    private final int x = 200;
    private int y;
    private int score = 0;
    private int animFrame;
    private int jumpHeight = 40;
    private boolean isDead = false;
    private Bitmap spritesheet = null;


    public Player(Bitmap bitmap, int y)
    {
        this.y = y;
        this.animFrame = 0;
        this.spritesheet = bitmap;

    }

    public void setAnimFrame() {
       this.animFrame++;
    }

    public void setAnimFrame(int number) {
        this.animFrame = number;
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

    public int getNumberOfSprites() { return this.numberOfSprites; }

    public boolean getIsDead() {return this.isDead;}

    public void setIsDead(boolean isDead){ this.isDead = isDead;}

    public Bitmap getSpritesheet() {
        return  this.spritesheet;
    }

    public void setScore() { this.score++; }

    public int getScore() {
        return this.score;
    }
}
