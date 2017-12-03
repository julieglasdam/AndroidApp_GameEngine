package com.example.julieglasdam.gameengine.Breakout;

/**
 * Created by julieglasdam on 24/10/2017.
 */

public interface CollisionListener {
    public void collisionWall();
    public void collisionPaddle();
    public void collisionBlock();
}
