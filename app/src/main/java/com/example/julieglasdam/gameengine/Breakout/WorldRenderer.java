package com.example.julieglasdam.gameengine.Breakout;

import android.graphics.Bitmap;

import com.example.julieglasdam.gameengine.GameEngine;

/**
 * Created by julieglasdam on 10/10/2017.
 */

public class WorldRenderer {
    GameEngine gameEngine;
    World world;
    Bitmap ballImage;

    public WorldRenderer(GameEngine ge, World w) {
        gameEngine = ge;
        world = w;
        ballImage = gameEngine.loadBitmap("breakoutassets/ball.png");
    }

    public void render() {
        gameEngine.drawBitMap(ballImage, world.ball.x, world.ball.y);
    }
}
